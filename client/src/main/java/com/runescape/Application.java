package com.runescape;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AppletContext;
import java.applet.AppletStub;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class Application extends JFrame implements AppletStub {

    private Class<?> clnt;
    private Applet v_client;

    public static void main(String[] args) throws Exception {
        Application app = new Application();
        app.startApplet();
    }

    public Application() {
        super(Configuration.CLIENT_NAME);
        setPreferredSize(new Dimension(783, 542));
    }

    private void startApplet() {
        try {
            clnt = Class.forName("com.runescape.Client");
            v_client = (Applet) clnt.getConstructor().newInstance();
            clnt.getSuperclass().getMethod("setStub", AppletStub.class).invoke(v_client, this);

            add(v_client);
            pack();
            setVisible(true);

            clnt.getMethod("init").invoke(v_client);
            clnt.getMethod("start").invoke(v_client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void appletResize(int width, int height) {
        try {
            clnt.getSuperclass().getMethod("resize", Integer.class, Integer.class).invoke(v_client, width, height);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AppletContext getAppletContext() {
        return null;
    }

    @Override
    public URL getCodeBase() {
        try {
            return new URL("127.0.0.1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getParameter(String name) {
        return null;
    }

    @Override
    public URL getDocumentBase() {
        try {
            return new URL("127.0.1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }



}