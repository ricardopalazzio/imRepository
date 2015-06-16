/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfood.imrepository.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author palazzio
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
@MultipartConfig(location = "/home/ubuntu/temp" , fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 8, maxRequestSize = 1024 * 1024 * 3 * 3)
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        for (Part part : request.getParts()) {
            String filename = "";
            for (String s : part.getHeader("content-disposition").split(";")) {
                if (s.trim().startsWith("filename")) {
                    filename = s.split("=")[1].replaceAll("\"", "");
                }
            }
            System.out.println(filename);
            part.write(filename);
        }
    }
}
