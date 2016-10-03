angular.module("AppMod", []).controller("AppCtrl", [function() {
	var self = this;
	self.about = "AppMod";
	
	self.changeAbout = function() {
		for (var std of self.students) {
			std.vis = true;
		}
	};
	
	self.hideBySat = function(sat) {
		for (var std of self.students) {
			console.log("called function!");
			if(std.sat>=sat) {
				std.show=true;
			} else {
				std.show=false;
			}
		}
	}
	
	self.refresh = function() {
		for(var std of self.students) {
			std.show = true;
		}
		$("#sat-filter").val("");
	}
	
	self.students = [
		{"id":100,"firstname":"Eric","lastname":"Ephram","gpa":3.0,"sat":1200,"majorid":1,"show":true},
		{"id":110,"firstname":"Greg","lastname":"Gould","gpa":2.5,"sat":1100,"majorid":null,"show":true},
		{"id":120,"firstname":"Adam","lastname":"Ant","gpa":3.2,"sat":1300,"majorid":null,"show":true},
		{"id":130,"firstname":"Howard","lastname":"Hess","gpa":3.7,"sat":1600,"majorid":4,"show":true},
		{"id":140,"firstname":"Charles","lastname":"Caldwell","gpa":2.1,"sat":900,"majorid":null,"show":true},
		{"id":150,"firstname":"James","lastname":"Joyce","gpa":2.5,"sat":1100,"majorid":null,"show":true},
		{"id":160,"firstname":"Doug","lastname":"Dumas","gpa":3.1,"sat":1350,"majorid":2,"show":true},
		{"id":170,"firstname":"Kevin","lastname":"Kraft","gpa":2.7,"sat":1000,"majorid":null,"show":true},
		{"id":180,"firstname":"Frank","lastname":"Fountain","gpa":2.5,"sat":1000,"majorid":null,"show":true},
		{"id":190,"firstname":"Brian","lastname":"Biggs","gpa":2.3,"sat":950,"majorid":null,"show":true}
		];
}]);