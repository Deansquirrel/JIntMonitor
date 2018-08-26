function setCurrMenuList(id){
	$(id).removeClass("collapsed");
	$(id).addClass("active");
	$(id).attr("aria-expanded","true");
}
function setCurrMenuPanel(id){
	$(id).addClass("in");
}
function setCurrMenuNode(id){
	$(id).addClass("active");
}

function showLoading(){
	swal({
		text:"处理中 ，请稍后",
		allowEscapeKey:false,
		allowOutsideClick:false,
		showConfirmButton:true,
		showCancelButton:false,
		showLoaderOnConfirm:true
	});
	swal.enableLoading();
}

function closeLoading(){
	swal.close();
}

function updateMenuPath(list){
	for(var i=0;i<list.length;i++){
		$("#view-breadcrumb").append('<li>' + list[i] + '</li>');
	}
}