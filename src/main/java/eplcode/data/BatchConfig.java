package eplcode.data;
import eplcode.model.Match;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import javax.sql.DataSource;



@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;


    private final String[] fieldNames = new String[]{
            "MatchID", "Season_End_Year", "Wk", "Date", "Home", "HomeGoals", "AwayGoals", "Away", "FTR",
    };

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>().name("MatchItemReader")
                .resource(new ClassPathResource("premier-league-matches-updated.csv")).delimited().names(fieldNames)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
                    {
                        setTargetType(MatchInput.class);
                    }
                })
                .linesToSkip(1)
                .build();
    }

    @Bean
    public MatchProcessor processor() {
        return new MatchProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO match (match_ID, season, match_week, date, home_team, away_team, full_time_home_team_goals, full_time_away_team_goals, full_time_result) " +
                        "VALUES (:matchID, :season, :matchWeek, :date, :homeTeam, :awayTeam, :fullTimeHomeTeamGoals, :fullTimeAwayTeamGoals, :fullTimeResult)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Step matchStep(JdbcBatchItemWriter<Match> writer) {
        return stepBuilderFactory
                .get("matchStep")
                .<MatchInput, Match>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();

}

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step matchStep) {
        return jobBuilderFactory
                .get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(matchStep)
                .end()
                .build();
    }






}
