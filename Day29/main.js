//The webpage will prompt you for your slack token!
$(document).ready(function() {
	
	var url = "https://slack.com/api/";

	$("#token").val(getSlackToken());
	
	$("#user-div").hide();
	$("#navbar").hide();
	$(".home-div").hide();

		
	var ajaxCall = function(apiMethod, options) {
		return $.ajax(url + apiMethod, options);
	}
	
	$("#verify-token").click(function() {
		var token = $("#token").val();
		var data = {"token":token};
		var options = {"data":data,method:"POST"};
		ajaxCall("auth.test",options).done(function(result) {
			if(result.ok) {
				onVerify();
				$("#token-div").hide();
				$("#navbar").show();
				$(".home-div").show();
			}
			else {
				alert("Token not valid");
			}
		});
	});

	var buildMsgData = function(channel,text) {
		var data = {"token":$("#token").val(), "channel":channel, "text":text};
		data.as_user = getAsUser();
		if (getBotName()!=="") {data.username = getBotName();}
		if (getIconUrl()!=="") {data.icon_url = getIconUrl();}
		return data;
	}
	
	function getAsUser() {
		return !($("#as-bot").is( ":checked" ));
	}
	
	function getBotName() {
		return $("#bot-name").val();
	}
	
	function getIconUrl() {
		return $("#bot-pic").val();
	}
	
	function onVerify() {
		token = $("#token").val().toString();
		var data = {"token":token};
		var options = {"data":data, method:"POST"};
		ajaxCall("channels.list",options).done(function(result) {
			result.channels.sort();
			for (var i=0;i<result.channels.length;i++) {
				var channelName = result.channels[i].name;
				var item = $("<li><input type=\"radio\" name=\"channel\" value=\"" + channelName + "\"> " + channelName + "</li>")
				$("#pub-chan-list").append(item);
			}
			
			ajaxCall("groups.list",options).done(function(result) {
				for (var i=0;i<result.groups.length;i++) {
					var channelName = result.groups[i].name;
					console.log(result.groups[i].id);
					var item = $("<li><input type=\"radio\" name=\"channel\" value=\"" + result.groups[i].id + "\"> " + channelName + "</li>")
					$("#pri-chan-list").append(item);
				}
			}).done(function(result) {	
				var radioButtons = $("input[name=channel]");
				$("#send-msg").click( function() {
					var chan = radioButtons.filter(":checked").val();
					var txt = $("#msg-box").val();
					var data = buildMsgData(chan, txt);
					console.log(chan);
						var options = {"data":data, method:"POST"};
					ajaxCall("chat.postMessage",options).done(function(results) {
						console.log(results);
						$("#msg-box").val("");
					});
				});
			});
		});
	}
	
	$("#clear-msg").click( function() {
		$("#msg-box").val("");
	});
	
	$("#nav-home").click(function(){
		$("#user-div").hide();
		$(".home-div").show();
	});
	
	$("#nav-info").click(function(){
		$("#user-div").show();
		$(".home-div").hide();
	});
	
	$("#nav-toggle").click(function(){
		$("#pri-chan-list").toggle();
	});

});