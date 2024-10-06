package com.sparta.inflearn.repository;

import com.sparta.inflearn.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JdbcMemberRepository implements MemberRepository {


    private DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();

            if(rs.next()){
                member.setId(rs.getLong(1));
            }
            else{
                throw new SQLException("id 조회 실패");
            }
            return member;
        }
        catch (Exception e){
            throw  new IllegalStateException(e.getMessage());
        }
        finally {
            close(conn,ps,rs);
        }
    }

    private Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement ps, ResultSet rs){
        try{
            if(rs != null){
                rs.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(ps != null){
                ps.close();
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn != null){
               close(conn);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException{
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);

            rs = ps.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            else{
                return Optional.empty();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            close(conn,ps,rs);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            else{
                return Optional.empty();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(conn,ps,rs);
        }
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close(conn,ps,rs);
        }
        return List.of();
    }
}
