angular.module("AppMod", []).controller("AppCtrl", ["$http", function($http) {
	var self = this;
	self.about = "AppMod";
	self.editRecord = null;
	
	self.showDiv = {
		table: true,
		edit: false,
		add: false
	};
	
	self.showTable = function() {
		self.showDiv.table = true;
		self.showDiv.edit = false;
		self.showDiv.add = false;
	}
	
	self.showEdit = function(id) {
		$http.get("http://localhost:8080/student/"+id).then(function(resp) {
			self.editRecord = resp.data;
		});
		self.showDiv.table = false;
		self.showDiv.edit = true;
		self.showDiv.add = false;
	}
	
	self.showAdd = function() {
		self.showDiv.table = false;
		self.showDiv.edit = false;
		self.showDiv.add = true;
	}
	
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
	
	$http.get("http://localhost:8080/student").then(function(resp) {
		self.students = resp.data;
		for (var std of self.students) {
			std.show = true;
		}
	}, function(err) {});
	
	self.deleteId = function(id) {
		$http({
			url:"http://localhost:8080/student/delete/" + id,
			method: "DELETE",
			data: null,
			crossorigin: true,
			dataType: "json",
			contentType: "application/json",
			}).then(function(resp) {
			location.reload(true);
		});
	}
	
	self.update = function() {
		var data = {
			id: $("#student-id").val(),
			firstName: $("#student-first-name").val(),
			lastName: $("#student-last-name").val(),
			gpa: $("#student-gpa").val(),
			sat: $("#student-sat").val()	
		};
		$http({
			url:"http://localhost:8080/student/update",
			method: "PUT",
			data: JSON.stringify(data),
			crossorigin: true,
			dataType: "json",
			contentType: "application/json",
			}).then(function(resp) {
			location.reload(true);
		});
	}
	
	self.add = function() {
		var data = {
			firstName: $("#add-student-first-name").val(),
			lastName: $("#add-student-last-name").val(),
			gpa: $("#add-student-gpa").val(),
			sat: $("#add-student-sat").val()	
		};
		$http({
			url:"http://localhost:8080/student/add",
			method: "POST",
			data: JSON.stringify(data),
			crossorigin: true,
			dataType: "json",
			contentType: "application/json",
			}).then(function(resp) {
			location.reload(true);
		});
	}
}]);