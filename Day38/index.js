angular.module("AppMod", ['ngRoute'])
	.factory("EditService",function() {
		var id = 100;
		
		return {
			getId: function() {
				return id;
			},
			
			setId: function(val) {
				id=val;
			}
		};
})
	.controller("AppCtrl", ["$http","$location","EditService", function($http,$location,EditService) {
		var self = this;
	
		self.goEdit = function(id) {
			EditService.setId(id);
			$location.path("/edit");
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
		});
	
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
}])
	.controller("EditCtrl", ["$http","EditService", function($http,EditService) {
		var self=this;
		self.editRecord = null;
		self.id = EditService.getId();
		
		$http.get("http://localhost:8080/student/"+self.id).then(function(resp) {
			self.editRecord = resp.data;
		});
}])
	.config(['$routeProvider', function($routeProvider) {

	$routeProvider
	.when('/', {
		templateUrl: 'views/home.view.html'

	}).when('/student', {
		templateUrl: 'views/student.view.html',
		controller: 'AppCtrl',
		controllerAs: 'ctrl'

	}).when('/about', {
		templateUrl: 'views/about.view.html'

	}).when('/add', {
		templateUrl: 'views/add.view.html'
	}).when('/edit', {
		templateUrl: 'views/edit.view.html',
		controller: 'EditCtrl',
		controllerAs: 'Ectrl'
	})
	.otherwise({redirectTo: '/'});

}]);