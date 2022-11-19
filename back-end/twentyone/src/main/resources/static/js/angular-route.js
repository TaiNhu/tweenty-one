const app = angular.module("app", ["ngRoute"])

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl: "/components/home.html",
		controller: "homeCtrl"
	})
	.when("/categories", {
		templateUrl: "/components/categories.html"
	})
	.when("/signin", {
		templateUrl: "/components/login.html",
		controller: "loginCtrl"
	})
	.when("/signup", {
		templateUrl: "/components/signup.html",
		controller: "signupCtrl"
	})
	.when("/animedetail", {
		templateUrl: "/components/anime-detail.html"
	})
	.when("/animewatch", {
		templateUrl: "/components/anime-watch.html"
	})
	.when("/logout", {
		templateUrl: "/comonents/home.html",
	})
})