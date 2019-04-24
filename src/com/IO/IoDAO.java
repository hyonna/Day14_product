package com.IO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.product.ProductDTO;
import com.util.DBConnector;

public class IoDAO {
	
	
	
	// ------------------------ INSERT
	
	public int insert(IoDTO ioDTO) throws Exception {
		
		int result = 0;
		
		Connection con = DBConnector.getConnet();
		
		String sql = "insert into IO values(product_seq.nextval,?,?,?,?,?)";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, ioDTO.getNum());
		st.setInt(2, ioDTO.getPnum());
		st.setInt(3, ioDTO.getIn_pct());
		st.setString(4, ioDTO.getIn_date());
		st.setInt(5, ioDTO.getOut_pct());
		st.setString(6, ioDTO.getOut_date());

		result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;
				
			
	}
	
	
	
	public ArrayList<IoDTO> select_list(int num) throws Exception {
		
		
		IoDTO ioDTO = null;
		
		ArrayList<IoDTO> ar = new ArrayList<IoDTO>();
		
		Connection con = DBConnector.getConnet();
		
		String sql = "select * from IO order by pnum desc";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			
			ioDTO = new IoDTO();
			ioDTO.setNum(rs.getInt("Num"));
			ioDTO.setPnum(rs.getInt("Pnum"));
			ioDTO.setIn_pct(rs.getInt("in_pct"));
			ioDTO.setIn_date(rs.getString("in_date"));
			ioDTO.setOut_pct(rs.getInt("out_pct"));
			ioDTO.setOut_date(rs.getString("out_date"));

			ar.add(ioDTO);
		}

		DBConnector.disConnect(rs, st, con);

		return ar;
		
	}
	
	
	public IoDTO select_one(int num) throws Exception {
		
		int result = 0;
		ResultSet rs = null;
		IoDTO ioDTO = null;

		Connection con = DBConnector.getConnet();

		String sql = "select * from product where pnum=?";

		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, num);

		rs = st.executeQuery();

		if (rs.next()) {

			ioDTO = new IoDTO();
			ioDTO.setNum(rs.getInt("Num"));
			ioDTO.setPnum(rs.getInt("Pnum"));
			ioDTO.setIn_pct(rs.getInt("in_pct"));
			ioDTO.setIn_date(rs.getString("in_date"));
			ioDTO.setOut_pct(rs.getInt("out_pct"));
			ioDTO.setOut_date(rs.getString("out_date"));

		}

		DBConnector.disConnect(rs, st, con);

		return ioDTO;
		
		
	}

}
