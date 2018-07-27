package com.culicode.dating.mewmew.practice;

import com.culicode.dating.mewmew.domain.User;
import com.culicode.dating.mewmew.domain.UserRole;
import com.culicode.dating.mewmew.rest.UserApiImpl;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
    private static final Logger LOG = LogManager.getLogger(Test.class.getName());
    public static void main(String[] args) {
      UserApiImpl userApi = new UserApiImpl();

    }

}
