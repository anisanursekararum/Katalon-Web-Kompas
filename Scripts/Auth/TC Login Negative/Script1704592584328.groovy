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

WebUI.waitForPageLoad(3)

String loginUrl = WebUI.getUrl()

assert loginUrl.contains(GlobalVariable.loginUrl)

WebUI.verifyElementVisible(findTestObject('Login Page/inputEmail'))

WebUI.verifyElementVisible(findTestObject('Login Page/inputPassword'))

def sourceData = findTestData('Data Files/Test Data/NegativeLoginData-Excel')

for (def rowNumber = 1; rowNumber <= sourceData.getRowNumbers(); rowNumber++) {
    email = sourceData.getValue(1, rowNumber)

    password = sourceData.getValue(2, rowNumber)

    WebUI.setText(findTestObject('Login Page/inputEmail'), email)

    WebUI.setText(findTestObject('Login Page/inputPassword'), password)

    WebUI.verifyElementClickable(findTestObject('Login Page/buttonLogin'))
	
	WebUI.click(findTestObject('Login Page/buttonLogin'))
	
	WebUI.delay(3)

    if (password.isEmpty()) {
		
		WebUI.waitForElementVisible(findTestObject('Login Page/textErrorEmpty'), 5)
		
        errorTextPassword = GlobalVariable.messagesEmptyPass

        WebUI.verifyElementVisible(findTestObject('Login Page/textErrorEmpty'))

        WebUI.verifyElementText(findTestObject('Login Page/textErrorEmpty'), errorTextPassword)
		
    } else if (email.isEmpty()) {
		
        errorTextEmail = GlobalVariable.messagesEmptyEmail

        WebUI.verifyElementVisible(findTestObject('Login Page/textErrorEmpty'))

        WebUI.verifyElementText(findTestObject('Login Page/textErrorEmpty'), errorTextEmail)
		
    } //else if (email != GlobalVariable.email){
		
//		alertError = WebUI.getAlertText()
		
//		String message = findTestObject('Login Page/textInvalidCredential').getAttribute(GlobalVariable.messagesInvalidEmailFormat);
		
//		WebUI.verifyMatch(alertError, GlobalVariable.messagesInvalidEmailFormat, false)
		
		//WebUI.verifyTextPresent(GlobalVariable.messagesInvalidEmailFormat, false)
		
	//}
	 else if ((email != GlobalVariable.email) || (password != GlobalVariable.password)) {
		
		WebUI.waitForElementVisible(findTestObject('Login Page/textInvalidCredential'), 5)
		
        errorTextInvalidCredential = GlobalVariable.messagesInvalidCredential

        WebUI.verifyElementVisible(findTestObject('Login Page/textInvalidCredential'))

        WebUI.verifyElementText(findTestObject('Login Page/textInvalidCredential'), errorTextInvalidCredential)
    }
}

