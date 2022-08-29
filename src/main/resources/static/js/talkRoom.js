let send = (e) => {
	e.preventDefault();
	
	const sayUser = parseInt($("#userId").val());
	const listenUser = parseInt($("#friendId").val());
	const message = $("textarea[name=message]").val();
	
	if(message === ""){
		alert("メッセージを入力してください");
	}else{
		let jsonData = {
			sayUser: sayUser,
			listenUser: listenUser,
			message: message
		}
		
		
		$.ajax({
			type: "POST",
			url: "/chatApp/api/sendMessage",
			data: JSON.stringify(jsonData),
			contentType: "application/json",
			scriptCharset: "utf-8",
		}).then((result) => {
			let json = JSON.parse(result)
			showMessages(json);
			$("textarea[name=message]").val("");
		}, () =>{
			console.error("Error: ajax通信に失敗しました。");
		});
	}
	
	
}

let showMessages = (json) => {
	const sayUser = parseInt($("#userId").val());
	const listenUser = parseInt($("#friendId").val());
	
	let table = "";
	json.forEach((j) => {
		if(j.sayUser === sayUser){
			table += `<tr><td>${j.loginName}</td><td>${j.message}</td></tr>`;
		} else if(j.sayUser === listenUser){
			table += `<tr><td>${j.friendName}</td><td>${j.message}</td></tr>`;
		}
	})
	
	$("#messageList").empty();
	$("#messageList").append(table);
}








