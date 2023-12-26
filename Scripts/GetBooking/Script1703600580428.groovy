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

response = WS.sendRequest(findTestObject('POST/Create_Booking', [('firstname') : 'jim', ('lastname') : 'Brown', ('totalprice') : '111'
            , ('depositpaid') : true, ('checkin') : '2018-01-01', ('checkout') : '2019-01-01', ('additionalneeds') : 'Breakfast']))

// Mendapatkan isi body dari respons
def responseBody = response.getResponseBodyContent()

// Pem-parsing isi body respons dengan JsonSlurper
def jsonSlurper = new groovy.json.JsonSlurper()

def jsonResult = jsonSlurper.parseText(responseBody)

// Mengambil nilai dari elemen 'bookingid'
def bookingId = jsonResult.bookingid

// Cetak nilai 'bookingid'
WS.comment("Booking ID: $bookingId")

response2 = WS.sendRequest(findTestObject('GET/GetBooking', [('id') : bookingId]))
// Mendapatkan isi body dari respons
def responseBody2 = response2.getResponseBodyContent()

// Pem-parsing isi body respons dengan JsonSlurper
def jsonResult2 = jsonSlurper.parseText(responseBody2)

// Cetak isi body dari respons 'GET/GetBooking'
WS.comment("Response from GET/GetBooking:\n$responseBody2")

// Verifikasi isi body respons dengan struktur JSON yang diinginkan
def expectedJson = '{"firstname":"jim","lastname":"Brown","totalprice":111,"depositpaid":true,"bookingdates":{"checkin":"2018-01-01","checkout":"2019-01-01"},"additionalneeds":"Breakfast"}'

// Pem-parsing struktur JSON yang diinginkan
def expectedJsonResult = jsonSlurper.parseText(expectedJson)

// Bandingkan isi dari respons dengan struktur JSON yang diinginkan
WS.verifyResponseStatusCode(response, 200)
assert jsonResult2 == expectedJsonResult



