@Grab('org.jsoup:jsoup:1.7.1')

import org.jsoup.Jsoup;
import groovy.json.JsonOutput

def URL = request.url

def getPage(location){

	response = Jsoup.connect(location)
	.ignoreContentType(true)
	.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36")
	.referrer("http://www.google.co.in/")
	.timeout(12000)
	.header("Host","www.moneycontrol.com")
	.header("Pragma","no-cache")
	.header("Cache-Control","no-cache")
	.header("Upgrade-Insecure-Requests","www.moneycontrol.com")
	.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
	.header("Accept-Encoding","gzip, deflate")
	.header("Accept-Language","en-US,en;q=0.8")
	.followRedirects(true)
	.execute();

	return response.parse();
}


def getBalanceSheet(url){

	def matcher = url =~ /^http.*stockpricequote\/.*\/(.*)\/(.*)/
	if( matcher.matches() ) {
		def balanceSheetURL = "http://www.moneycontrol.com/financials/${matcher[0][1]}/balance-sheet/${matcher[0][2]}"
		println balanceSheetURL
		return getPage(balanceSheetURL)
	}

}

def getNumber(doc2, el, item){	
	
	el = doc2.select(el+":matches(^${item}\$)").first()
	if(el != null){
		str = el.nextElementSibling().text()
		//println "${item} => ${str}"
		try{
			return java.text.NumberFormat.instance.parse(str).toFloat();
		}catch(NumberFormatException){ return -0.1f }
	}else{
		return 0.0f;
	}
}

def getStockInfo(doc, doc2){

	def stock = [:]

	stock.marketCap = getNumber(doc, 'div', 'MARKET CAP \\(Rs Cr\\)')
	try{
		stock.price = doc.select('#Nse_Prc_tick').text().toFloat()
	}catch(NumberFormatException nfe){
		stock.price = doc.select('#Bse_Prc_tick').text().toFloat()
	}
	
	stock.eps = getNumber(doc, 'div', 'EPS \\(TTM\\)')
	
	//doc.select("div:matches(^P/E\$)").get(1).nextElementSibling().text().toFloat()
	stock.bookValue = getNumber(doc2, 'td', 'Book Value \\(Rs\\)')
	
	stock.dy = getNumber(doc, 'div', 'DIV YIELD.\\(%\\)')
	stock.fv = getNumber(doc, 'div', 'FACE VALUE \\(Rs\\)')
	
	stock.currentAssets = getNumber(doc2, 'td', 'Total CA, Loans & Advances')
	//Total CL & Provisions
	stock.currentLiabilities = getNumber(doc2, 'td', 'Total CL & Provisions')

	//Net Current Assets
	stock.netCurrentAssets = getNumber(doc2, 'td', 'Net Current Assets')	

	stock.netWorth = getNumber(doc2, 'td', 'Networth')

	stock.totalDebt = getNumber(doc2, 'td', 'Total Debt')

	// price to earning ratio
	stock.pe = (stock.price / stock.eps).round(3)

	//stock.pbv = getNumber(doc, 'div', 'PRICE/BOOK')
	stock.pbv = (stock.price / stock.bookValue).round(3)

	//debt to equity ratio
	stock.de = (stock.totalDebt/stock.netWorth).round(3)

	//debt to current asset ratio
	stock.dnca = (stock.totalDebt / stock.netCurrentAssets).round(3)

	//current assets to current liabilites ratio
	stock.cacl = (stock.currentAssets / stock.currentLiabilities).round(3)

	return stock 
}


doc = getPage(URL);
doc2 = getBalanceSheet(URL)

stock = getStockInfo(doc, doc2);

//println stock
response.stock = JsonOutput.toJson(stock)