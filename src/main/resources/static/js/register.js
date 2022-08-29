$(() => {
	$("#registerBtn").on('click', register);
})

let register = (e) => {
	e.preventDefault();
	
	if($("input[name=userName]").val() === ""){
		alert("ユーザー名を入力してください。");
	} else if ($("input[name=password]").val() === ""){
		alert("パスワードを入力してください。");
	} else {
		let jsonData = {
			userName: $("input[name=userName]").val(),
			password: $("input[name=password]").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/chatApp/api/register",
			data: JSON.stringify(jsonData),
			contentType: "application/json",
			scriptCharset: "utf-8",
		}).then(() => {
			$("input[name=userName]").val("");
			$("input[name=password]").val("");
			alert("ユーザーを作成しました。")
		}, () =>{
			console.error("Error: ajax通信に失敗しました。");
		});
	}

}