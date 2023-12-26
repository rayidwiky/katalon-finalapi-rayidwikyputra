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
import groovy.json.JsonBuilder as JsonBuilder
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS


// Data yang disertakan langsung dalam skrip
def data = [[('firstname') : firstname, ('lastname') : lastname, ('totalprice') : totalprice, ('depositpaid') : depositpaid
        , ('checkin') : checkin, ('checkout') : checkout, ('additionalneeds') : additionalneeds]]


// Iterasi melalui setiap baris data
data.each { rowData ->
	def firstname = rowData.firstname
	def lastname = rowData.lastname
	def totalprice = rowData.totalprice
	def depositpaid = rowData.depositpaid
	def checkin = rowData.checkin
	def checkout = rowData.checkout
	def additionalneeds = rowData.additionalneeds


def response = WS.sendRequest(findTestObject('POST/Create_Booking', [('firstname') : firstname, ('lastname') : lastname, ('totalprice') : totalprice
		, ('depositpaid') : depositpaid, ('checkin') : checkin, ('checkout') : checkout, ('additionalneeds') : additionalneeds]))

// Cetak data ke log
WS.comment("Firstname: $firstname, Lastname: $lastname, Totalprice: $totalprice, Depositpaid: $depositpaid, Checkin: $checkin, Checkout: $checkout, Additionalneeds: $additionalneeds")

// Verifikasi status kode respons
WS.verifyResponseStatusCode(response, 200)

// Mendapatkan isi body dari respons
def responseBody = response.getResponseBodyContent()
WS.comment(responseBody)

// Pemisahan setiap elemen dalam body respons
def jsonSlurper = new groovy.json.JsonSlurper()
def jsonResult = jsonSlurper.parseText(responseBody)
// Cetak setiap elemen secara terpisah
WS.comment("Booking ID: ${jsonResult.bookingid}")
WS.verifyResponseStatusCode(response, 200)
def booking = jsonResult.booking
WS.comment("Firstname: ${booking.firstname}")
booking.firstname==firstname
WS.comment("Lastname: ${booking.lastname}")
booking.lastname==lastname
WS.comment("Totalprice: ${booking.totalprice}")
booking.totalprice==totalprice
WS.comment("Depositpaid: ${booking.depositpaid}")
booking.depositpaid==depositpaid

def bookingdates = booking.bookingdates
WS.comment("Checkin: ${bookingdates.checkin}")
bookingdates.checkin=checkin
WS.comment("Checkout: ${bookingdates.checkout}")
bookingdates.checkout=checkout

WS.comment("Additionalneeds: ${booking.additionalneeds}")
booking.additionalneeds=additionalneeds

}


