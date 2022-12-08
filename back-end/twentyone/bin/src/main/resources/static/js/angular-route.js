const app = angular.module("app", ["ngRoute"])

app.config(function($routeProvider) {
	$routeProvider.when("/", {
		templateUrl: "/components/home.html",
		controller: "homeCtrl"
	})
	.when("/categories/:category", {
		templateUrl: "/components/categories.html",
		controller: "categoriesCtrl"
	})
	.when("/signin", {
		templateUrl: "/components/login.html",
		controller: "loginCtrl"
	})
	.when("/signup", {
		templateUrl: "/components/signup.html",
		controller: "signupCtrl"
	})
	.when("/animedetail/:id", {
		templateUrl: "/components/anime-detail.html",
		controller: "animeDetailCtrl"
	})
	.when("/watchanime/:id", {
		templateUrl: "/components/anime-watch.html",
		controller: "watchAnimeCtrl"
	})
	.when("/logout", {
		templateUrl: "/components/home.html"
	})
	.when("/profile", {
		templateUrl: "/components/profile.html",
		controller: "profileCtrl"
	})
	.when("/animelist", {
		templateUrl: "/components/animelist.html",
		controller: "animelistCtrl"
	})
})