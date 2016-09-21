$(document).ready(function() {
	var number = 0;
	var numBox = $("#number");
	var inc = $("#increment");
	var dec = $("#decrement");
	var res = $("#reset");
	var delta = $("#delta");
	var zeroMsg = $("#zero_message");
	
	function randomDelta() {
		return Math.floor(Math.random() * 21) - 10;
	}
	
	delta.val(randomDelta());
	
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
});