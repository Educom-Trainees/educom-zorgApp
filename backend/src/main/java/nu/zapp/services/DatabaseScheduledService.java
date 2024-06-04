package nu.zapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseScheduledService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void callInactivityCheckProcedure() {
        jdbcTemplate.execute("CALL inactivity_check()");
    }

    public void createInactivityCheckProcedure() {
        dropInactivityCheckProcedure();
        String sql = "CREATE PROCEDURE inactivity_check() " +
                "BEGIN " +
                "UPDATE customer " +
                "SET postalcode = '-', address = '-', residence = '-' " +
                "WHERE inactivation_date <= DATE_SUB(CURDATE(), INTERVAL 30 DAY); " +
                "END";

        jdbcTemplate.execute(sql);
    }

    public void dropInactivityCheckProcedure() {
        jdbcTemplate.execute("DROP PROCEDURE IF EXISTS inactivity_check");
    }
}