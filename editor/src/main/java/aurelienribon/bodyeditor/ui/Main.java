package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Canvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.json.JSONException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Main {

    public static void main(final String[] args) {

//        if (SharedLibraryLoader.isMac && Gdx.app == null) {
//            Configuration.GLFW_CHECK_THREAD0.set(false);
//            //Configuration.GLFW_LIBRARY_NAME.set(GlfwAWTLoader.load().getAbsolutePath());
//            Configuration.GLFW_LIBRARY_NAME.set("glfw_async");
//        }

//        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
//        config.setForegroundFPS(60);
//        config.setTitle("Canvas");
//
//        config.setWindowListener(new Lwjgl3WindowAdapter() {
//            @Override
//            public void created(Lwjgl3Window window) {
//                super.created(window);
//
//                SwingUtilities.invokeLater(() -> {
//                    try {
//                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
//                             UnsupportedLookAndFeelException ignored) {
//                    }
//
//                    MainWindow mw = Ctx.window;
//
//                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//                    mw.setSize(
//                            Math.min(1150, screenSize.width - 100),
//                            Math.min(800, screenSize.height - 100)
//                    );
//
//                    mw.setLocationRelativeTo(null);
//                    mw.setVisible(true);
//
//                    parseArgs(args);
//
//                    GLFW.glfwMakeContextCurrent(window.getWindowHandle());
//                    GL.createCapabilities();
//                });
//            }
//        });
//        new Lwjgl3Application(new Canvas(), config);

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.forceExit = false;
        config.title = "Editor Canvas";
        new LwjglApplication(new Canvas(), config);

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

//            LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//            config.resizable = false;
//            LwjglCanvas glCanvas = new LwjglCanvas(new Canvas(), config);

            MainWindow mw = Ctx.window;

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mw.setSize(
                    Math.min(1150, screenSize.width - 100),
                    Math.min(800, screenSize.height - 100)
            );
//            mw.setMinimumSize(new Dimension(
//                    Math.min(1145, screenSize.width - 100),
//                    Math.min(795, screenSize.height - 100)
//            ));

//            mw.setCanvas(glCanvas.getCanvas());
            mw.setLocationRelativeTo(null);
            mw.setVisible(true);

            parseArgs(args);
        });

    }

    private static void parseArgs(String[] args) {
        for (int i = 1; i < args.length; i++) {
            if (args[i - 1].equals("-f")) {
                try {
                    File file = new File(args[i]).getCanonicalFile();
                    Ctx.io.setProjectFile(file);
                    Ctx.io.importFromFile();
                } catch (IOException | JSONException ignored) {
                }
            }
        }
    }
}
