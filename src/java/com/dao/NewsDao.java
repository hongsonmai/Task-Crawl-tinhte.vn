/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.Tintuc;
import com.ulti.DBUlti;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Son
 */
public class NewsDao {

    private Connection connection;

    public NewsDao() {
        try {
            connection = DBUlti.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getInfo() {
        try {
            ArrayList<Tintuc> array = new ArrayList<Tintuc>();
            Document doc = Jsoup.connect("https://tinhte.vn/").timeout(5000).get();
            Elements threads = doc.getElementsByClass("threads");
            for (Element thread : threads) {
                Elements posts = thread.children();
                if (posts.hasAttr("id")) {
                    for (Element post : posts.subList(0, 13)) {
                        if (post.attr("id").trim().contains("post")) {
                            Tintuc newss = new Tintuc();
                            Elements imgs = post.getElementsByClass("thread-image");
                            for (Element img : imgs) {
                                newss.setImg(img.attr("data-src"));
                            }
                            Elements titles = post.getElementsByClass("thread-title");
                            for (Element title : titles) {
                                Elements titleInside = title.children();
                                for (Element titleInside1 : titleInside) {
                                    newss.setTitle(titleInside1.ownText());
                                }
                            }
                            Elements contents = post.getElementsByClass("messageText");
                            for (Element content : contents) {
                                Elements contentInside = content.children();
                                for (Element contentInside1 : contentInside) {
                                    newss.setContent(contentInside1.ownText());
                                    newss.setLink(contentInside1.attr("href"));
                                }
                            }
                            String abc = post.attr("id").substring(5);
                            newss.setIdPost(abc);
                            if (checkID(abc)) {
                                array.add(newss);
                            }
                        }
                    }
                }
            }
            for (int i = array.size() - 1; i >= 0; i--) {
                insert(array.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkID(String idPost) {
        try {
            String SQL = "Select IDPost From Tintuc Where IDPost = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, idPost);
            ResultSet rs = ps.executeQuery();
            if (rs.isBeforeFirst() == false) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void insert(Tintuc news) {
        try {
            String SQL = "Insert Into Tintuc(Title,Content,Img,Link,IDPost) Values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setString(3, news.getImg());
            ps.setString(4, news.getLink());
            ps.setString(5, news.getIdPost());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Tintuc news) {
        try {
            String SQL = "Update Tintuc Set Title = ?, Content = ?, Img = ? Where ID = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setString(3, news.getImg());
            ps.setInt(4, news.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        try {
            String SQL = "Delete From Tintuc Where ID = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Tintuc> getAllTintuc() {
        List<Tintuc> news = new ArrayList<Tintuc>();
        try {

            String SQL = "Select Top 10 * From Tintuc Order By ID Desc ";
            Statement s = connection.createStatement();

            ResultSet rs = s.executeQuery(SQL);
            while (rs.next()) {
                Tintuc newss = new Tintuc();
                newss.setId(rs.getInt("ID"));
                newss.setTitle(rs.getString("Title"));
                newss.setImg(rs.getString("Img"));
                newss.setContent(rs.getString("Content"));
                newss.setLink(rs.getString("Link"));
                news.add(newss);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return news;
    }

    public Tintuc getTinByID(int id) {
        Tintuc news = null;
        try {
            String SQL = "Select * From Tintuc Where ID = ?";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String title = rs.getString("Title");
                String img = rs.getString("Img");
                String content = rs.getString("Content");

                news = new Tintuc(id, title, content, img);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NewsDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return news;
    }

}
