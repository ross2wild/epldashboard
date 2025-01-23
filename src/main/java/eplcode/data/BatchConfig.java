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
            "MatchID", "Season", "MatchWeek", "Date", "Time", "HomeTeam",
            "AwayTeam", "FullTimeHomeTeamGoals", "FullTimeAwayTeamGoals", "FullTimeResult",
            "HalfTimeHomeTeamGoals", "HalfTimeAwayTeamGoals", "HalfTimeResult", "Referee", "HomeTeamShots",
            "AwayTeamShots", "HomeTeamShotsOnTarget", "AwayTeamShotsOnTarget", "HomeTeamCorners",
            "AwayTeamCorners", "HomeTeamFouls", "AwayTeamFouls", "HomeTeamYellowCards", "AwayTeamYellowCards",
            "HomeTeamRedCards", "AwayTeamRedCards", "B365HomeTeam", "B365Draw", "B365AwayTeam", "B365Over2point5Goals",
            "B365UnderpointGoals", "MarketMaxHomeTeam", "MarketMaxDraw", "MarketMaxAwayTeam", "MarketAvgHomeTeam", "MarketAvgDraw",
            "MarketAvgAwayTeam", "MarketMaxOverpointGoals", "MarketMaxUnderpointGoals", "MarketAvgOverpointGoals", "MarketAvgUnderpointGoals",
            "HomeTeamPoints", "AwayTeamPoints"
    };

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>().name("MatchItemReader")
                .resource(new ClassPathResource("PremierLeague.csv")).delimited().names(fieldNames)
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
                .sql("INSERT INTO match (match_ID, season, match_week, date, time, home_team, away_team, full_time_home_team_goals, full_time_away_team_goals, full_time_result, half_time_home_team_goals, half_time_away_team_goals, half_time_result, referee, home_team_shots, away_team_shots, home_team_shots_on_target, away_team_shots_on_target, home_team_corners, away_team_corners, home_team_fouls, away_team_fouls, home_team_yellow_cards, away_team_yellow_cards, home_team_red_cards, away_team_red_cards, home_team_points, away_team_points) " +
                        "VALUES (:matchID, :season, :matchWeek, :date, :time, :homeTeam, :awayTeam, :fullTimeHomeTeamGoals, :fullTimeAwayTeamGoals, :fullTimeResult, :halfTimeHomeTeamGoals, :halfTimeAwayTeamGoals, :halfTimeResult, :referee, :homeTeamShots, :awayTeamShots, :homeTeamShotsOnTarget, :awayTeamShotsOnTarget, :homeTeamCorners, :awayTeamCorners, :homeTeamFouls, :awayTeamFouls, :homeTeamYellowCards, :awayTeamYellowCards, :homeTeamRedCards, :awayTeamRedCards, :homeTeamPoints, :awayTeamPoints)")
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
