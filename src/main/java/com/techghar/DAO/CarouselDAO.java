package com.techghar.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.CarouselItem;

public class CarouselDAO {
	private Connection conn = null;

	public CarouselDAO() throws ClassNotFoundException, SQLException {

		this.conn = DatabaseConnection.getDatabaseConnection();
	}

	public ArrayList<CarouselItem> getCarouselItems() throws SQLException {

		ArrayList<CarouselItem> items = new ArrayList<CarouselItem>();

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM carousel_items");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				CarouselItem item = new CarouselItem(rs.getString("title"), rs.getString("description"),
						rs.getString("image_url"));
				items.add(item);
			}

			conn.close();

	

		return items;
	}

}
