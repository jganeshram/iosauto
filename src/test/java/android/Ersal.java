package android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;


/**
 * Created by GaneshRam J on 01/08/17.
 */
public class Ersal {
    public static AppiumDriverLocalService service;
    public static AndroidDriver driver;



    @BeforeSuite
    public void beforeClass() throws Exception, IOException {
//local path of node folder
        String Appium_Node_Path = "/usr/local/bin/node";
//local path of main.js
        String Appium_JS_Path = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js";

        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                     // .withIPAddress("127.0.0.1")
                       // .usingPort(4723)
                        .usingAnyFreePort()
                .usingDriverExecutable(new File(Appium_Node_Path))
                .withAppiumJS(new File (Appium_JS_Path))
                );

      service.start();
      service.isRunning();

//     if (service == null || service.isRunning()) {
//         throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
//     }



        File appDir = new File("src/test/resources/file/android");
        File app = new File(appDir, "base.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
       capabilities.setCapability("--session-override", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability("deviceName","Nexus5");
       capabilities.setCapability("avd", "Nexus5");
        capabilities.setCapability("Appium-version", "1.6.5");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.fetchr.driverapp");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.fetchr.driverapp.screens.authentication.LoginActivity");
       capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,"com.fetchr.driverapp.screens.authentication.LoginActivity");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, WaitFor.Unit.HOUR);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
       // driver = new AndroidDriver(service.getUrl(), capabilities);
        driver = new AndroidDriver(service.getUrl(), capabilities);
    }

   @AfterSuite
   public  void afterClass() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
    @Test
    public void login(){
//driver.findElement(By.id("android:id/button1")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("com.fetchr.driverapp:id/mEdtUsername")));
driver.findElement(By.id("com.fetchr.driverapp:id/mEdtUsername")).sendKeys("ganesh");
       // wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Allow")));
      //  driver.findElement(By.id("Allow")).click();
    }

}
