app.controller("ctrl", function($scope, $http, $route) {
	
	
	
	$scope.user = JSON.parse(localStorage.getItem("user") || "{}")
	
	$scope.logout = function() {
		$http.get("/logout").then(json => {
			$scope.user = {}
			localStorage.removeItem("user")
		},
		error => {
			console.log(error)
		})
	}
	
	$http.get("/login").then(json => {
		
		$scope.user = json.data
		localStorage.setItem("user", JSON.stringify($scope.user))
	},
	error => {
		console.log(error)
	}
	)
	
	
	
})

app.controller("signupCtrl", function($scope, $http) {
	
	$scope.error = null
	
	$scope.signup = function() {
		$http({
			method: "POST",
			url: "/registration",
			headers: {
				'Content-Type': 'application/json'
			},
			data: JSON.stringify($scope.userForSignUp)
		}).then(data => {
			$scope.error = 2
		},
		error => {
			$scope.error = 1
			console.log(error);
		})
	}
})

app.controller("loginCtrl", function($scope, $http, $window) {
	$scope.error = null
	
	$scope.login = function() {
		
		$http({
			method: "POST",
			url: "/login",
			headers: {
				'Content-Type': 'application/json'
			},
			data: JSON.stringify($scope.userLogin)
		}).then(data => {
			$scope.error = false
			localStorage.setItem("user", JSON.stringify(data.data))
			$window.location.href = '/index.html';
		},
		error => {
			$scope.error = true
			console.log(error);
		})
	}
	
})
