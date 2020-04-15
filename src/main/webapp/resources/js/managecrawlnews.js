function checkclick(dlg){
	if (PF('wgtable').selection.length > 0) {
		PF(dlg).show()
	} else {
		PF('alert').renderMessage({"summary":"Lỗi",
            "detail":"Hãy chọn 1 bản ghi",
            "severity":"warn"})
	}
}
function renderMessageCrawlNow(){
	PF('alert').renderMessage({"summary":"Thông báo",
        "detail":"Đang crawl ngay",
        "severity":"info"})
}