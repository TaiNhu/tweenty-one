const interceptor = ($q, $location) => ({
	request: config => {
		return config
	},
	requestError: function(rejection) {
		return $q.reject(rejection);
	},
	response: result => {
		return result
	},
	responseError: function(rejection) {
		return $q.reject(rejection);
	}
})

app.config(function($httpProvider) {
	$httpProvider.interceptors.push(interceptor)
})

app.controller("homeCtrl", function($scope, $http) {


	$scope.lastestFilm = []
	$scope.liveActionFilm = []
	$scope.popularFilm = []
	$scope.films = []
	$scope.lastestComment = []
	$scope.user = JSON.parse(localStorage.getItem("user") || "{}")


	$http.get("/films/lastest").then(data => {
		$scope.film = data.data
		$scope.lastestFilm = data.data.slice(0, 6)
	})

	$http.get("/films/type?types=8").then(data => {
		$scope.liveActionFilm = data.data
	})

	$http.get("/films/type?sort=count,desc").then(data => {
		$scope.popularFilm = data.data.slice(0, 6)
	})

	$http.get("/films/lastestcomment").then(data => {
		$scope.lastestComment = data.data
	})

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
			if(data.data.role == "USER") {
				$window.location.href = '/index.html';
			} else if (data.data.role = "ADMIN") {
				$window.location.href = '/admin/tables.html/#!/';
			}

		},
			error => {
				$scope.error = true
				console.log(error);
			})
	}

})

app.controller("categoriesCtrl", function($scope, $http, $routeParams) {
	$scope.films = []
	$scope.originFilms = []
	$scope.categories = []
	$scope.popularFilm = []
	$scope.pages = []
	$scope.currentPage = ($routeParams.page || 1)
	let divice = 6
	$scope.lastestComment = []
	$scope.nameFilter = $routeParams.name

	$http.get("/films/lastestcomment").then(data => {
		$scope.lastestComment = data.data
	})

	$http.get("/films/type?sort=count,desc").then(data => {
		$scope.popularFilm = data.data.slice(0, 6)
	})
	$scope.change = function() {

		$http.get(`/films/categories?${$routeParams.category != 0 ? 'categories=' + $routeParams.category : ''}&sort=${$scope.sort}${!$scope.nameFilter ? '' : '&name=' + $scope.nameFilter}`)
			.then(data => {
				$scope.currentPage = 1
				$scope.originFilms = data.data
				$scope.films = data.data.slice(($scope.currentPage - 1) * divice, ($scope.currentPage - 1) * divice + divice)
				loadPage();
			})
	}
	$http.get(`/categories/`).then(data => {
		$scope.categories = data.data
		$scope.category = data.data.filter(v => v.id == $routeParams.category)[0]
	})
	$http.get(`/films/categories?${$routeParams.category != 0 ? 'categories=' + $routeParams.category : ''}${!$scope.nameFilter ? '' : '&name=' + $scope.nameFilter}`)
		.then(data => {
			$scope.originFilms = data.data
			$scope.films = data.data.slice(($scope.currentPage - 1) * divice, ($scope.currentPage - 1) * divice + divice)
			loadPage();
		})

	$scope.changePage = function(page) {
		$scope.currentPage = page
		$scope.films = $scope.originFilms.slice(($scope.currentPage - 1) * divice, ($scope.currentPage - 1) * divice + divice)
		loadPage();
	}

	function loadPage() {
		$scope.pages = []
		$scope.pages.push($scope.currentPage)
		for (let i = 1; i < 3; i++) {
			if ($scope.currentPage > 0 && $scope.currentPage <= Math.ceil($scope.originFilms.length / divice)) {
				if ($scope.currentPage - i > 0) {
					$scope.pages.push($scope.currentPage - i)
				}
				if ($scope.currentPage + i <= Math.ceil($scope.originFilms.length / divice)) {
					$scope.pages.push($scope.currentPage + i)
				}
			}

		}
		$scope.pages.sort((a, b) => a - b)
	}
})

app.controller("animeDetailCtrl", function($scope, $http, $routeParams, $location) {
	$scope.films = []
	$scope.user = JSON.parse(localStorage.getItem("user") || "{}")
	$scope.enabled = false
	$scope.categories = null
	$scope.recommend = 0
	$scope.commentDisabled = false
	$scope.avatar = $scope.user.userName && $scope.user.image.includes("http") && $scope.user.image


	if ($routeParams.id) {
		$http.get(`/films/${$routeParams.id}`).then(data => {
			if (data.data.length <= 0) {
				$location.path("")
			} else {
				$scope.films = data.data
				$scope.commentDisabled = data.data[0].reviews.some(v => v.user.userName == $scope.user.userName)

				$scope.deleteComment = id => {
					$http({
						method: "DELETE",
						url: "/reviews/" + id
					}).then(data => {
						$scope.films[0].reviews = $scope.films[0].reviews.filter(value => value.id != id)
						$scope.commentDisabled = false
						$scope.recommend = 0
						$scope.recommend = data.data[0].reviews.reduce((previous, current) => {
							if (current.recommend) {
								return ++previous;
							}
							return previous;
						}, 0)
					}, error => console.log(error))
				}

				$scope.categories = data.data[0].videoGenres.map(v => v.category.name).join(", ")
				$http.get(`/films/avgscore/${$routeParams.id}`).then(data => {
					$scope.films[0].avg = data.data[0] || 0
				})
				$scope.recommend = data.data[0].reviews.reduce((previous, current) => {
					if (current.recommend) {
						return ++previous;
					}
					return previous;
				}, 0)


				if ($scope.user.userName) {
					$http.get(`/animelist/${$scope.user.userName}/${$scope.films[0].id}`)
						.then(data => {
							if (data.data.length > 0) $scope.enabled = true
							else $scope.enabled = false
							$scope.followFilm = function(anime) {
								if ($scope.user.userName) {
									if (!$scope.enabled) {
										$http({
											method: "POST",
											url: "/animelist/modify",
											headers: {
												'Content-Type': 'application/json'
											},
											data: JSON.stringify({
												status: "plantowatch",
												typeVideo: anime.id,
												user: $scope.user.userName
											})
										}).then(data => {
											if (data.status == 200) {
												$scope.enabled = true
											} else {
												alert("Something wrong")
											}
										}, error => console.log(error))
									} else if ($scope.enabled) {
										console.log("/animelist/modify/" + anime.id + "/" + $scope.user.userName)
										$http({
											method: "DELETE",
											url: "/animelist/modify/" + anime.id + "/" + $scope.user.userName
										}).then(data => {
											if (data.status == 200) {
												$scope.enabled = false
											} else {
												alert("Something wrong")
											}
										}, error => console.log(error))
									}

								}
							}
						})
				}
			}
		},
			error => {
				console.log(error)
			})

		$scope.postComment = function() {
			if ($scope.user.userName) {
				let review = $scope.review
				let film = { ...$scope.films[0] }
				delete film.avg
				delete film.$$hashKey
				delete review.$$hashKey
				film.reviews = []
				review.typeVideo = film
				review.user = $scope.user
				$http({
					method: "POST",
					url: "/reviews/",
					headers: {
						'Content-Type': 'application/json'
					},
					data: JSON.stringify(review)
				}).then(data => {
					$scope.films[0].reviews = data.data
					if (review.recommend) $scope.recommend++
					$scope.commentDisabled = true
				}, error => {
					console.log(error)
				})
			}

		}



	}

})

app.controller("ctrl", function($scope, $http, $window) {

	$scope.user = JSON.parse(localStorage.getItem("user") || "{}")
	$scope.avatar = null
	$http.get("/login").then(json => {

		$scope.user = json.data
		localStorage.setItem("user", JSON.stringify($scope.user))
		if ($scope.user.image.includes("http")) {
			$scope.avatar = $scope.user.image
		}
	},
		error => {
			$scope.user = {}
			localStorage.removeItem("user");
		}
	)
	$scope.logout = function() {
		$http.get("/logout").then(json => {
			$scope.user = {}
			$scope.avatar = null
			localStorage.removeItem("user")
		},
			error => {
				console.log(error)
			})
	}

	$scope.submit = function() {
		$window.location.href = "/#!categories/0?name=" + $scope.search
		document.querySelector(".search-close-switch").click()
	}

})

app.controller("watchAnimeCtrl", function($scope, $http, $routeParams) {
	$scope.user = JSON.parse(localStorage.getItem("user") || "{}")
	$scope.films = []
	$scope.link = null
	$scope.commentDisabled = false
	$scope.currentEpisode = $routeParams.episode
	$http.get(`/films/${$routeParams.id}`).then(data => {
		console.log(data)
		
		if (data.data.length <= 0) {
			$location.path("")
		} else {
			$scope.films = data.data
			if (!$scope.currentEpisode) {
				$scope.currentEpisode = data.data[0].episodes[0].id
				let video = document.querySelector("video")
				video.load()
				$http({
					method: "POST",
					url: "/films",
					headers: {
						'Content-Type': 'application/json'
					},
					data: JSON.stringify({ ...data.data[0], count: data.data[0].count + 1 })
				})
			}
			$http.get("/episodes/"+ $scope.currentEpisode, {
				headers: {
					'Range': 0
				}
			}).then(function (data){
				console.log(data)
				$scope.link = data.data
			})
			$scope.commentDisabled = data.data[0].reviews.some(v => v.user.userName == $scope.user.userName)
		}
	},
		error => {
			console.log(error)
		})
	$scope.postComment = function() {
		if ($scope.user.userName) {
			let review = $scope.review
			let film = { ...$scope.films[0] }
			delete film.avg
			delete film.$$hashKey
			delete review.$$hashKey
			film.reviews = []
			review.typeVideo = film
			review.user = $scope.user
			$http({
				method: "POST",
				url: "/reviews/",
				headers: {
					'Content-Type': 'application/json'
				},
				data: JSON.stringify(review)
			}).then(data => {
				$scope.films[0].reviews.push(review)
				$scope.commentDisabled = true
			}, error => {
				console.log(error)
			})
		}

	}
})

app.controller("profileCtrl", function($scope, $http, $routeParams, $location) {
	$scope.user = $routeParams.user || JSON.parse(localStorage.getItem("user") || "{}").userName
	$scope.authen = false
	if ($scope.user) {
		$http.get("/user/" + $scope.user).then(data => {
			$scope.user = data.data
			let local = JSON.parse(localStorage.getItem("user") || "{}")
			if (data.data.userName == local.userName && data.data.password == local.password) {
				$scope.authen = true
				$scope.changeImage = function() {
					if ($scope.authen) {
						let formData = new FormData()
						let image = document.querySelector("label>input[type='file']")
						formData.append("image", image.files[0])
						$http({
							method: "POST",
							url: "/saveimage/avatar",
							headers: {
								'Content-Type': undefined
							},
							data: formData
						}).then(a => {
							$scope.user = a.data
							$scope.avatar = (() => {
								if ($scope.user.image.includes("http")) return $scope.user.image
								return "/img/" + $scope.user.image
							})()
							document.querySelector("#avatar").src = $scope.avatar
						})
					}
				}
			}
			$scope.avatar = (() => {
				if ($scope.user.image.includes("http")) return $scope.user.image
				return "/img/" + $scope.user.image
			})()
		}, error => {
			console.log(error)
		})
		$scope.animeList = []
		$http.get("/animelist/" + $scope.user).then(data => {
			$scope.animeList = data.data.slice(0, 5)
		}, error => console.log(error))
	} else {
		$location.path("/")
	}

})

app.controller("animelistCtrl", function($scope, $http, $routeParams, $location) {
	$scope.user = $routeParams.user || JSON.parse(localStorage.getItem("user") || "{}").userName
	$scope.authen = false
	if ($scope.user) {
		let closeEditInfo = document.querySelector("#close_edit_info")
		$scope.animeList = []
		$http.get(`/animelist/${$scope.user}`).then(data => {
			$scope.animeList = data.data
			$scope.originList = data.data
			$http.get("/user/" + $scope.user).then(data => {
				if (data.data.userName == local.userName && data.data.password == local.password) {
					$scope.authen = true
					$scope.showEdit = function(anime) {
						$scope.currentAnime = { ...anime, score: anime.score / 2 }
					}
					$scope.editAnime = () => {
						if ($scope.currentAnime.id) {
							$http.put(
								"/animelist/modify/" + $scope.currentAnime.id,
								JSON.stringify({ ...$scope.currentAnime, score: $scope.currentAnime.score * 2 }), {
								'Content-Type': 'application/json'
							}).then(data => {
								$scope.animeList[$scope.animeList.findIndex(value => value.id == data.data.id)] = data.data
								$scope.currentAnime = {}
								closeEditInfo.click()
							}, error => console.log(error))
						}
					}
					$scope.deleteAnime = () => {
						if ($scope.currentAnime.id) {
							$http({
								method: "DELETE",
								url: "/animelist/modify/" + $scope.currentAnime.typeVideo.id + "/" + $scope.user
							}).then(data => {
								$scope.animeList = $scope.animeList.filter(value => value.id != $scope.currentAnime.id)
								$scope.currentAnime = {}
								closeEditInfo.click()
							}, error => console.log(error))
						}
					}
				}
			})
			$scope.filterAnimeList = function(filter, search) {
				if (!search) {
					if (!filter) {
						$scope.animeList = $scope.originList
					} else {
						$scope.animeList = $scope.originList.filter(value => value.status == filter)
					}
				} else {
					$scope.animeList = $scope.originList.filter(value => value.typeVideo.name.toLowerCase().includes($scope.searchAnimeList.toLowerCase()))
				}
			}
		}, error => console.log(error))

	} else {
		$location.path("/")
	}

})