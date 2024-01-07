import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser(GlobalVariable.baseUrl)

WebUI.setViewPortSize(GlobalVariable.viewportWidth, GlobalVariable.viewportHeight)

WebUI.delay(7)

WebUI.switchToFrame(findTestObject('Home Page/iframeGoogle'), 5)

WebUI.verifyElementClickable(findTestObject('Home Page/buttonClose'))

WebUI.click(findTestObject('Home Page/buttonClose'))

WebUI.switchToWindowUrl(GlobalVariable.baseUrl, FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementPresent(findTestObject('Home Page/logo'), 0)

WebUI.verifyElementClickable(findTestObject('Home Page/iconAccount'))

WebUI.click(findTestObject('Home Page/iconAccount'))

WebUI.verifyElementVisible(findTestObject('Home Page/menuLogin'))

WebUI.click(findTestObject('Home Page/menuLogin'))

String loginUrl = WebUI.getUrl()

assert loginUrl.contains(GlobalVariable.loginUrl)

WebUI.verifyElementVisible(findTestObject('Login Page/inputEmail'))

WebUI.verifyElementVisible(findTestObject('Login Page/inputPassword'))

WebUI.setText(findTestObject('Login Page/inputEmail'), GlobalVariable.email)

WebUI.setText(findTestObject('Login Page/inputPassword'), GlobalVariable.password)

WebUI.verifyElementClickable(findTestObject('Login Page/buttonLogin'))

WebUI.click(findTestObject('Login Page/buttonLogin'))

welcomeText = ('Selamat datang,\n' + GlobalVariable.Username)

WebUI.verifyElementVisible(findTestObject('Login Page/wordingSuccessLogin'))

WebUI.verifyElementText(findTestObject('Login Page/wordingSuccessLogin'), welcomeText)

WebUI.verifyElementText(findTestObject('Login Page/textSuccessLogin'), GlobalVariable.successMessages)

WebUI.verifyElementVisible(findTestObject('Login Page/buttonLanjutkan'))

WebUI.verifyElementClickable(findTestObject('Login Page/buttonLanjutkan'))

WebUI.delay(5)

String successLoginUrl = WebUI.getUrl()

assert successLoginUrl.contains(GlobalVariable.baseUrl)

WebUI.closeBrowser()

