package com.example.demo.repo;

import com.example.demo.dto.AccountRequest;
import com.example.demo.dto.BeneficiarySubmitRequest;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class BeneRepo {

    private final DataSource dataSource;

    public BeneRepo(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public boolean existByBeneNameBeneID(String customerId, String beneficiaryName) throws SQLException {


        String sql = """
                SELECT COUNT(1)
                FROM BENEFICIARY_MASTER
                WHERE CUSTOMER_ID = ?
                AND BENE_NAME = ?
                """;


        try (Connection connection = dataSource.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, customerId);
            ps.setString(2, beneficiaryName);


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }

        return false;
    }


    public int save(BeneficiarySubmitRequest request) throws SQLException {

        String beneSql = """
                INSERT INTO BENE_TXN
                (
                    CUSTOMER_ID,
                    CORPORATE_ID,
                    BENE_NAME,
                    NICK_NAME,
                    BENE_TYPE,
                    BENE_CATEGORY,
                    MOBILE_NO,
                    EMAIL_ID,
                    STATUS,
                    FAVOURITE_FLAG,
                    DAILY_LIMIT,
                    MONTHLY_LIMIT,
                    TXN_LIMIT,
                    CREATED_BY,
                    CREATED_DATE
                )
                VALUES
                (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP
                )
                """;

        String accountSql = """
                INSERT INTO BENEFICIARY_ACCOUNT
                (
                    BENE_ID,
                    ACCOUNT_NO,
                    ACCOUNT_NAME,
                    ACCOUNT_TYPE,
                    BANK_NAME,
                    BRANCH_NAME,
                    IFSC_CODE,
                    CURRENCY,
                    ACCOUNT_STATUS,
                    DEFAULT_FLAG,
                    CREATED_BY,
                    CREATED_DATE
                )
                VALUES
                (
                    ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP
                )
                """;

        try (Connection connection = dataSource.getConnection()) {

            connection.setAutoCommit(false);

            try {

                int beneId;

                try (PreparedStatement ps = connection.prepareStatement(beneSql, Statement.RETURN_GENERATED_KEYS)) {

                    ps.setString(1, request.getCustomerId());
                    ps.setString(2, request.getCorporateId());
                    ps.setString(3, request.getBeneficiaryName());
                    ps.setString(4, request.getNickName());
                    ps.setString(5, request.getBeneficiaryType());
                    ps.setString(6, request.getBeneficiaryCategory());
                    ps.setString(7, request.getMobileNumber());
                    ps.setString(8, request.getEmail());
                    ps.setString(9, "APPROVE");
                    ps.setString(10, request.getFavourite());
                    ps.setBigDecimal(11, request.getDailyLimit());
                    ps.setBigDecimal(12, request.getMonthlyLimit());
                    ps.setBigDecimal(13, request.getTransactionLimit());
                    ps.setString(14, request.getCreatedBy());

                    int rows = ps.executeUpdate();

                    if (rows == 0) {
                        throw new SQLException("Beneficiary insert failed.");
                    }

                    try (ResultSet rs = ps.getGeneratedKeys()) {

                        if (rs.next()) {
                            beneId = rs.getInt(1);
                        } else {
                            throw new SQLException("Failed to retrieve generated BENE_ID.");
                        }
                    }
                }

                try (PreparedStatement accountPs = connection.prepareStatement(accountSql)) {

                    for (AccountRequest account : request.getAccounts()) {

                        accountPs.setInt(1, beneId);
                        accountPs.setString(2, account.getAccountNumber());
                        accountPs.setString(3, account.getAccountName());
                        accountPs.setString(4, account.getAccountType());
                        accountPs.setString(5, account.getBankName());
                        accountPs.setString(6, account.getBranchName());
                        accountPs.setString(7, account.getIfscCode());
                        accountPs.setString(8, account.getCurrency());
                        accountPs.setString(9, "ACTIVE");
                        accountPs.setString(10, account.getDefaultFlag());
                        accountPs.setString(11, request.getCreatedBy());

                        accountPs.addBatch();
                    }

                    accountPs.executeBatch();
                }

                connection.commit();

                return beneId;

            } catch (Exception e) {

                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }
}