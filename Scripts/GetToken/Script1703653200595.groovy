import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonSlurper
import internal.GlobalVariable

// Melakukan permintaan POST ke CreateToken
def response = WS.sendRequest(findTestObject('POST/CreateToken'))

// Mendapatkan isi body dari respons
String responseBody = response.getResponseBodyContent()

// Pem-parsing JSON menggunakan JsonSlurper
def jsonSlurper = new JsonSlurper()
def jsonResponse = jsonSlurper.parseText(responseBody)

// Mendapatkan nilai token
def tokenValue = jsonResponse.token

// Menampilkan nilai token
println("Token Value: $tokenValue")

GlobalVariable.TOKEN = tokenValue
