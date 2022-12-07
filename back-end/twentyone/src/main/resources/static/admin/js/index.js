angular.module("chart", []).controller("chart1", function ($scope) {
      var ctx = document.querySelector('#doughnut').getContext('2d')
    
      var myChartPie = new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                      labels: ["Naruto", "Doremon", "Conan", "Shinosuke", "Onepiece", "Dragon Ball", "Hành động", "Siêu nhiên", "Hoạt hình", "Thiên nhiên", "Đánh nhau"],
                      datasets: [{
                        label: '# of Votes',
                        data: [2, 7, 3, 5, 2, 3,5,3,7,4,6],
                        backgroundColor: [
                          '#9999CC',
                          '#999999',
                          '#999966',
                          '#6699CC',
                          '#669999',
                          '#669966',
                          '#3399CC',
                          '#339999',
                          '#009999',
                          '#339966',
                          '#0099CC',
                        ],
                        borderWidth: 1
                      }]
                    },
                    options: {
                      scales: {
                      }
                    }
                  });


                  var ctx0 = document.querySelector('#bar').getContext('2d')
                  var ctx1 = document.querySelector('#bar1').getContext('2d')
                  var ctx2 = document.querySelector('#bar2').getContext('2d')
                  var ctx3 = document.querySelector('#bar3').getContext('2d')
            
                      var myChartColumn0 = new Chart(ctx0, {
                        type: 'bar',
                        data: {
                          labels: ["Naruto", "Doremon", "Conan", "Shinosuke", "Onepiece", "Dragon Ball"],
                          datasets: [{
                            label: '# of Votes',
                            data: [12, 19, 3, 5, 2, 3],
                            backgroundColor: [
                              '#CC33FF',
                              '#9933FF',
                              '#6633FF',
                              '#3333FF',
                              '#0033FF',
                            ],
                            
                        //     borderColor: [
                        //       'rgba(255,99,132,1)',
                        //       'rgba(54, 162, 235, 1)',
                        //       'rgba(255, 206, 86, 1)',
                        //       'rgba(75, 192, 192, 1)',
                        //       'rgba(153, 102, 255, 1)',
                        //       'rgba(255, 159, 64, 1)'
                        //     ],
                            borderWidth: 1
                          }]
                        },
                        options: {
                          scales: {
                            yAxes: [{
                              ticks: {
                                beginAtZero: true
                              }
                            }]
                          }
                        }
                      });
            
            
                      var myChartColumn1 = new Chart(ctx1, {
                        type: 'bar',
                        data: {
                          labels: ["Naruto", "Doremon", "Conan", "Shinosuke", "Onepiece", "Dragon Ball"],
                          datasets: [{
                            label: '# of Votes',
                            data: [10, 10, 5, 4, 2, 3],
                            backgroundColor: [
                              '#CC33FF',
                              '#9933FF',
                              '#6633FF',
                              '#3333FF',
                              '#0033FF',
                            ],
                            
                        //     borderColor: [
                        //       'rgba(255,99,132,1)',
                        //       'rgba(54, 162, 235, 1)',
                        //       'rgba(255, 206, 86, 1)',
                        //       'rgba(75, 192, 192, 1)',
                        //       'rgba(153, 102, 255, 1)',
                        //       'rgba(255, 159, 64, 1)'
                        //     ],
                            borderWidth: 1
                          }]
                        },
                        options: {
                          scales: {
                            yAxes: [{
                              ticks: {
                                beginAtZero: true
                              }
                            }]
                          }
                        }
                      });
            
                      var myChartColumn2 = new Chart(ctx2, {
                        type: 'bar',
                        data: {
                          labels: ["Naruto", "Doremon", "Conan", "Shinosuke", "Onepiece", "Dragon Ball"],
                          datasets: [{
                            label: '# of Votes',
                            data: [10, 10, 5, 4, 2, 3],
                            backgroundColor: [
                              '#CC33FF',
                              '#9933FF',
                              '#6633FF',
                              '#3333FF',
                              '#0033FF',
                            ],
                            borderWidth: 1
                          }]
                        },
                        options: {
                          scales: {
                            yAxes: [{
                              ticks: {
                                beginAtZero: true
                              }
                            }]
                          }
                        }
                      });
            
                      var myChartColumn3 = new Chart(ctx3, {
                        type: 'bar',
                        data: {
                          labels: ["Naruto", "Doremon", "Conan", "Shinosuke", "Onepiece", "Dragon Ball"],
                          datasets: [{
                            label: '# of Votes',
                            data: [10, 10, 5, 4, 2, 3],
                            backgroundColor: [
                              '#CC33FF',
                              '#9933FF',
                              '#6633FF',
                              '#3333FF',
                              '#0033FF',
                            ],
                            borderWidth: 1
                          }]
                        },
                        options: {
                          scales: {
                            yAxes: [{
                              ticks: {
                                beginAtZero: true
                              }
                            }]
                          }
                        }
                      });
    });