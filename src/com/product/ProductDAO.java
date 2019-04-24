package com.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.util.DBConnector;

public class ProductDAO {

	
	// ------------------------ INSERT
	
	public int insert(ProductDTO productDTO, Connection con) throws Exception {

		int result = 0;

		String sql = "insert into product values(product_seq.nextval,?,?,?,?)";

		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, productDTO.getPnum());
		st.setString(2, productDTO.getCategory());
		st.setString(3, productDTO.getPname());
		st.setInt(4, productDTO.getPrice());
		st.setInt(5, productDTO.getStock());

		result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;

	}
	
	
	
	// ------------------------ UPDATE

	public int update(ProductDTO productDTO, Connection con) throws Exception {

		Scanner sc = new Scanner(System.in);
		int result = 0;
		
		String sql = "update product set stock=stock+? where pnum=?";

		PreparedStatement st = con.prepareStatement(sql);
		
		st.setInt(1, productDTO.getStock());
		st.setInt(2, productDTO.getPnum());

		result = st.executeUpdate();

		DBConnector.disConnect(st, con);

		return result;

	}

	
	// ------------------------ SELECT_LIST
	
	public ArrayList<ProductDTO> select_list() throws Exception {

		Connection con = DBConnector.getConnet();

		String sql = "select * from product order by pnum desc";

		PreparedStatement st = con.prepareStatement(sql);

		ResultSet rs = st.executeQuery();

		ProductDTO productDTO = null;

		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();

		while (rs.next()) {
			productDTO = new ProductDTO();
			productDTO.setPnum(rs.getInt("pnum"));
			productDTO.setCategory(rs.getString("category"));
			productDTO.setPname(rs.getString("pname"));
			productDTO.setPrice(rs.getInt("price"));
			productDTO.setStock(rs.getInt("stock"));

			ar.add(productDTO);
		}

		DBConnector.disConnect(rs, st, con);

		return ar;

	}
	
	
	// ------------------------ SELECT_ONE

	public ProductDTO select_one(int num) throws Exception {

		int result = 0;
		ResultSet rs = null;
		ProductDTO productDTO = null;

		Connection con = DBConnector.getConnet();

		String sql = "select * from product where pnum=?";

		PreparedStatement st = con.prepareStatement(sql);

		st.setInt(1, num);

		rs = st.executeQuery();

		if (rs.next()) {

			productDTO = new ProductDTO();
			productDTO.setPnum(rs.getInt("pnum"));
			productDTO.setCategory(rs.getString("category"));
			productDTO.setPname(rs.getString("pname"));
			productDTO.setPrice(rs.getInt("price"));
			productDTO.setStock(rs.getInt("stock"));

		}

		DBConnector.disConnect(rs, st, con);

		return productDTO;

	}
}
