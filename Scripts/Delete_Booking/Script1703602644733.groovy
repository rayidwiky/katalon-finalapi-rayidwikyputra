import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import groovy.json.JsonSlurper
import internal.GlobalVariable

// 1. Mendapatkan token dengan permintaan POST ke CreateToken
def response = WS.sendRequest(findTestObject('POST/CreateToken'))

def responseBody = response.getResponseBodyContent()

// Pem-parsing JSON menggunakan JsonSlurper
def jsonSlurper = new JsonSlurper()

def jsonResponse = jsonSlurper.parseText(responseBody)

// Mengambil nilai token
def tokenValue = jsonResponse.token

// Menampilkan nilai token menggunakan WS.comment
WS.comment("Token Value: $tokenValue")

//ambil id
WebUI.callTestCase(findTestCase('GetBookingID'), [:], FailureHandling.STOP_ON_FAILURE)
WS.comment("${GlobalVariable.ID}")

def responsedelete = WS.sendRequest(findTestObject('Delete/DeleteBooking', [('token') : tokenValue, ('id') : GlobalVariable.ID]))

// Verifikasi status kode respons
WS.verifyResponseStatusCode(responsedelete, 201)


