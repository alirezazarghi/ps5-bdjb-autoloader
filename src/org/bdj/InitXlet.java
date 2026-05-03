package org.bdj;

import java.io.*;
import java.net.*;
import java.lang.*;
import java.lang.reflect.*;

import java.awt.BorderLayout;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;

import org.bdj.sandbox.Exploit;
import org.bdj.sandbox.ExploitInternal;

public class InitXlet implements Xlet {
    private HScene scene;
    private Screen screen;
    private InternalJarLoader internalJarLoader;
    private Thread internalJarLoaderThread;
    private final String jarLoaderThreadName = "JarLoader";
    
    public void initXlet(XletContext context) {
        
        Status.println("BD-J init");
        Status.setScreenOutputEnabled(true);
        Status.setNetworkLoggerEnabled(false);

        screen = Screen.getInstance();
        screen.setSize(1920, 1080);

        scene = HSceneFactory.getInstance().getDefaultHScene();
        scene.add(screen, BorderLayout.CENTER);
        scene.validate();
    }
    
    public void startXlet() {
        screen.setVisible(true);
        scene.setVisible(true);
        
        Status.println("Screen initialized");
        
        try {
            Status.println("Triggering sandbox escape exploit...");
            
            if (!Exploit.disableSecurityManager()) {
                ExploitInternal.disableSecurityManager();
            }

            Status.println(System.getSecurityManager() == null
                ? "Exploit success - sandbox escape achieved"
                : "Exploit failed - sandbox still active");

            // Warm up network stack to prevent dlopen failures in dynamic JARs
            try {
                Status.println("Warming up network stack...");
                InetAddress.getByName("127.0.0.1");
            } catch (Throwable ignored) {}

        } catch (Exception e) {
            Status.printStackTrace("Error when disabling sandbox: ", e);
        }
        
        // Add sanity check
        if (System.getSecurityManager() == null) {
            try {
                internalJarLoader = new InternalJarLoader();
                internalJarLoaderThread = new Thread(internalJarLoader, jarLoaderThreadName);
                internalJarLoaderThread.start();
            } catch (Throwable e) {
                Status.printStackTrace("Loader startup failed", e);
            }
        } else {
            Status.println("Sandbox is still activated");
        }
        
    }

    public void pauseXlet() {
        screen.setVisible(false);
    }

    public void destroyXlet(boolean unconditional) {
        scene.remove(screen);
        scene = null;
    }
    
    
}



