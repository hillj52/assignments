$(document).ready(function() {
	var number = 0;
	var numBox = $("#number");
	var inc = $("#increment");
	var dec = $("#decrement");
	var res = $("#reset");
	var delta = $("#delta");
	var zeroMsg = $("#zero_message");
	var byOne = $("#by_one");
	var byRandom = $("#by_random");
	var byUser = $("#by_user");
	var useDelta = false;
	var prevDelta = 1;
	
	function randomDelta() {
		prevDelta = delta.val();
		if (useDelta) {
			return Math.floor(Math.random() * 21) - 10;
		} else {
			return prevDelta;
		}
	}
	
	delta.val(randomDelta());
	$("#increment_control").hide();
	
	
	function toggleItalic() {
		if ((number % 2) === 0) {
			numBox.addClass("italic");
		} else {
			numBox.removeClass("italic");
		}
	}
	
	function hasA7(num) {
		var digits = [];
		var string = num.toString();
		for (var i=0;i<string.length;i++) {
			digits.push(string.charAt(i));
		}
		if (digits.indexOf("7") === -1) {
			return false;
		} else {
			return true;
		}
	}
	
	numBox.val(number);
	
	$("#buttons").click(function() {
		toggleItalic();
		delta.val(randomDelta());
		if(number===0) {
			zeroMsg.show();
		} else {
			zeroMsg.hide();
		}
	});
	
	inc.click(function() {
		number += Number( delta.val() );
		numBox.val(number);
		if (hasA7(number)) {
			numBox.removeClass("text-danger");
			numBox.addClass("green");
		} else {
			numBox.removeClass("green");
			if(number > 8) {
				numBox.addClass("text-danger");
			}
		}
	});
	
	dec.click(function() {
		number -= Number( delta.val() );
		numBox.val(number);
		if (hasA7(number)) {
			numBox.addClass("green");
		} else {
			numBox.removeClass("green");
			if(number < 9) {
			numBox.removeClass("text-danger");
			}
		}
	});
	
	res.click(function() {
		number = 0;
		numBox.val(number).removeClass("text-danger").addClass("italic");
		zeroMsg.show();
	});
	
	byOne.click(function() {
		byOne.addClass("active");
		byRandom.removeClass("active");
		byUser.removeClass("active");
		useDelta = false;
		$("#increment_control").hide();
		delta.val(1);
	});
	
	byRandom.click(function() {
		byOne.removeClass("active");
		byRandom.addClass("active");
		byUser.removeClass("active");
		useDelta = true;
		delta.val(randomDelta());
		$("#increment_control").show();
	});
	
	byUser.click(function() {
		byOne.removeClass("active");
		byRandom.removeClass("active");
		byUser.addClass("active");
		useDelta = false;
		$("#increment_control").show();
	});
});