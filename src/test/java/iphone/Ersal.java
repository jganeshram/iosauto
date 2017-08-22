package iphone;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;



/**
 * Created by GaneshRam J on 01/08/17.
 */
public class Ersal {
    public static AppiumDriverLocalService service;
    public static IOSDriver<IOSElement> driver;


    @BeforeClass
    public void beforeClass() throws Exception, IOException {
      //local path of node folder
        String Appium_Node_Path = "/usr/local/bin/node";
//local path of main.js
      //  String Appium_JS_Path = "/Applications/Appium 3.app/Contents/Resources/app/node_modules/appium/build/lib/main.js";
        String Appium_JS_Path = "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/build/lib/main.js";
        service = AppiumDriverLocalService
                .buildService(new AppiumServiceBuilder()
                               .usingAnyFreePort()
                               // .withIPAddress("0.0.0.0")
                .usingAnyFreePort()
                             //   .usingPort(4725)
                .usingDriverExecutable(new File(Appium_Node_Path))
                .withAppiumJS(new File (Appium_JS_Path))
                );

      service.start();
      service.isRunning();

//     if (service == null || service.isRunning()) {
//         throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
//     }



        File appDir = new File("src/test/resources/file/iphone");
        File app = new File(appDir, "Ersal.zip");
        DesiredCapabilities capabilities = new DesiredCapabilities();
       // capabilities.setCapability("--session-override", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
        capabilities.setCapability(IOSMobileCapabilityType.PLATFORM, "iOS");
        capabilities.setCapability(IOSMobileCapabilityType.VERSION, "10.3");
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "us.fetchr.ersal.CustomerApp-Alpha");
        capabilities.setCapability(IOSMobileCapabilityType.APP_NAME, "Ersal");
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, WaitFor.Unit.HOUR);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new IOSDriver(service.getUrl(), capabilities);
    }

   @AfterClass
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
driver.findElement(By.id("OK")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Allow")));
driver.findElement(By.id("Allow")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Allow")));
        driver.findElement(By.id("Allow")).click();
    }

}
