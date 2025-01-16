package eplcode.data;
import eplcode.model.Match;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final String[] fieldNames = new String[]{
            "MatchID", "Season", "MatchWeek", "Date", "Time", "HomeTeam",
            "AwayTeam", "FullTimeHomeTeamGoals", "FullTimeAwayTeamGoals", "FullTimeResult",
            "HalfTimeHomeTeamGoals", "HalfTimeAwayTeamGoals", "HalfTimeResult", "Referee", "HomeTeamShots",
            "AwayTeamShots", "HomeTeamShotsOnTarget", "AwayTeamShotsOnTarget", "HomeTeamCorners",
            "AwayTeamCorners", "HomeTeamFouls", "AwayTeamFouls", "HomeTeamYellowCards", "AwayTeamYellowCards",
            "HomeTeamRedCards", "AwayTeamRedCards", "HomeTeamPoints", "AwayTeamPoints"
    };

    @Bean
    public FlatFileItemReader<MatchInput> reader() {
        return new FlatFileItemReaderBuilder<MatchInput>()
                .name("matchItemReader")
                .resource(new ClassPathResource("PremierLeague.csv"))
                .delimited()
                .names(fieldNames)
                .targetType(MatchInput.class)
                .build();
    }

    @Bean
    public MatchProcessor processor() {
        return new MatchProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Match> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Match>()
                .sql("INSERT INTO match (match_ID, season, match_week, date, time, home_team, away_team, full_time_home_team_goals, fullTimeAwayTeamGoals, fullTimeResult, halfTimeHomeTeamGoals, halfTimeAwayTeamGoals, halfTimeResult, referee, homeTeamShots, awayTeamShots, homeTeamShotsOnTarget, awayTeamShotsOnTarget, homeTeamCorners, awayTeamCorners, homeTeamFouls, awayTeamFouls, homeTeamYellowCards, awayTeamYellowCards, homeTeamRedCards, awayTeamRedCards, homeTeamPoints, awayTeamPoints) VALUES ()")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
}
