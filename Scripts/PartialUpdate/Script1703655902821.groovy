import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

// Mengambil ID dari testcase 'GetBookingID'
WebUI.callTestCase(findTestCase('GetBookingID'), [:], FailureHandling.STOP_ON_FAILURE)

// Menampilkan nilai ID yang diambil
WS.comment("Booking ID: ${GlobalVariable.ID}")

// Mengambil token dari testcase 'GetToken'
WebUI.callTestCase(findTestCase('GetToken'), [:], FailureHandling.STOP_ON_FAILURE)

// Menampilkan nilai token yang diambil
WS.comment("Token Value: ${GlobalVariable.TOKEN}")

def responseUpdate = WS.sendRequest(findTestObject('PATCH/PartialUpdateBooking', [('token') : GlobalVariable.TOKEN, ('id') : GlobalVariable.ID]))

// Verifikasi status kode respons
if (responseUpdate.getStatusCode() == 200) {
	// Mendapatkan isi body dari respons update
	def responseBodyUpdate = responseUpdate.getResponseBodyContent()

	// Menampilkan hasil respons update
	WS.comment("Update Booking Response: $responseBodyUpdate")

	// Data yang diharapkan
	def expectedData = '{"firstname":"Josh","lastname":"Allen","totalprice":111,"depositpaid":true,"bookingdates":{"checkin":"2018-01-01","checkout":"2019-01-01"},"additionalneeds":"super bowls"}'
	WS.comment(expectedData)
	
	// Melakukan asertion untuk membandingkan hasil respons dengan data yang diharapkan
	if (responseBodyUpdate == expectedData) {
		// Menampilkan pesan jika asertion berhasil
		WS.comment("Update Booking matches the expected data.")
	} else {
		// Menampilkan pesan jika asertion gagal
		WS.comment("Update Booking does not match the expected data.")
	}
} else {
	// Menampilkan pesan jika status kode respons bukan 200
	WS.comment("Update Booking failed with status code: ${responseUpdate.getStatusCode()}")
}
