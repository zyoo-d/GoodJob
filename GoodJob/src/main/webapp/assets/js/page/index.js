"use strict";

//메인화면 방문자 통계 그래프
document.addEventListener('DOMContentLoaded', function() {
  var ctx = document.getElementById('myChart').getContext('2d');
  var myChart;

  function createChart(label, data, labels, yAxisStep = 100) {
    if (myChart) {
      myChart.destroy();
    }

    myChart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          label: label,
          data: data,
          borderWidth: 2,
          backgroundColor: 'transparent',
          borderColor: 'rgba(63,82,227,1)',
          pointBorderWidth: 0,
          pointRadius: 3.5,
          pointBackgroundColor: 'transparent',
          pointHoverBackgroundColor: 'rgba(63,82,227,1)',
          fill: false
        }]
      },
      options: {
        legend: {
          display: false
        },
        scales: {
          yAxes: [{
            gridLines: {
              drawBorder: false,
              color: '#f2f2f2',
            },
            ticks: {
              beginAtZero: true,
              stepSize: yAxisStep,
              callback: function(value, index, values) {
                return value;
              }
            }
          }],
          xAxes: [{
            gridLines: {
              display: false,
              tickMarkLength: 15,
            }
          }]
        },
      }
    });
  }

  const recentDays = ["2023-04-20", "2023-04-21", "2023-04-22", "2023-04-23", "2023-04-24", "2023-04-25", "2023-04-26", "2023-04-27", "2023-04-28", "2023-04-29"];
  createChart('최근 10일', [460, 458, 330, 502, 430, 610, 488, 560, 420, 550], recentDays);

  const monthLabels = function() {
    const labels = [];
    const startDate = new Date("2023-04-20");
    for (let i = 0; i < 10; i++) {
      const start = new Date(startDate.getTime() + i * 3 * 24 * 60 * 60 * 1000);
      const end = new Date(start.getTime() + 2 * 24 * 60 * 60 * 1000);
      labels.push(`${start.getMonth() + 1}-${start.getDate()}~${end.getMonth() + 1}-${end.getDate()}`);
    }
    return labels;
  };
  const monthData = [390, 600, 390, 280, 600, 450, 520, 570, 630, 690];
const yearLabels = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];
const yearData = [5500, 6000, 5800, 6000, 5700, 6200, 5900, 6400, 6100, 6600, 6300, 6800];

  const recentDaysBtn = document.getElementById('recentDaysBtn');
  const monthStatsBtn = document.getElementById('monthStatsBtn');
  const yearStatsBtn = document.getElementById('yearStatsBtn');

  recentDaysBtn.addEventListener('click', function() {
    createChart('최근 10일', [460, 458, 330, 502, 430, 610, 488, 560, 420, 550], recentDays);
  });

  monthStatsBtn.addEventListener('click', function() {
    createChart('한달 통계', monthData, monthLabels());
  });

  yearStatsBtn.addEventListener('click', function() {
    createChart('1년 통계', yearData, yearLabels, 1000); // y축 간격 1000으로 설정
  });
});

var ctx = document.getElementById("myChart3").getContext('2d');
var myChart = new Chart(ctx, {
  type: 'doughnut',
  data: {
    datasets: [{
      data: [
        80,
        40,
        20,
      ],
      backgroundColor: [
        '#63ed7a',
        '#fc544b',
        '#6777ef',
      ],
      label: 'Dataset 1'
    }],
    labels: [
      '질문',
      '스터디',
      '면접후기',
    ],
  },
  options: {
    responsive: true,
    legend: {
      position: 'top',
      
    },
  }
});

// 일일 방문자수 그래프 label에 날짜 data에 방문자수
var balance_chart = document.getElementById("balance-chart").getContext('2d');

var balance_chart_bg_color = balance_chart.createLinearGradient(0, 0, 0, 70);
balance_chart_bg_color.addColorStop(0, 'rgba(63,82,227,.2)');
balance_chart_bg_color.addColorStop(1, 'rgba(63,82,227,0)');

var myChart = new Chart(balance_chart, {
  type: 'line',
  data: {
    labels: ['16-07-2018', '17-07-2018', '18-07-2018', '19-07-2018', '20-07-2018', '21-07-2018', '22-07-2018', '23-07-2018', '24-07-2018', '25-07-2018', '26-07-2018', '27-07-2018', '28-07-2018', '29-07-2018', '30-07-2018', '31-07-2018'],
    datasets: [{
      label: 'Balance',
      data: [50, 61, 80, 50, 72, 52, 60, 41, 30, 45, 100, 40, 93, 63, 50, 62],
      backgroundColor: balance_chart_bg_color,
      borderWidth: 3,
      borderColor: 'rgba(63,82,227,1)',
      pointBorderWidth: 0,
      pointBorderColor: 'transparent',
      pointRadius: 3,
      pointBackgroundColor: 'transparent',
      pointHoverBackgroundColor: 'rgba(63,82,227,1)',
    }]
  },
  options: {
    layout: {
      padding: {
        bottom: -1,
        left: -1
      }
    },
    legend: {
      display: false
    },
    scales: {
      yAxes: [{
        gridLines: {
          display: false,
          drawBorder: false,
        },
        ticks: {
          beginAtZero: true,
          display: false
        }
      }],
      xAxes: [{
        gridLines: {
          drawBorder: false,
          display: false,
        },
        ticks: {
          display: false
        }
      }]
    },
  }
});

var sales_chart = document.getElementById("sales-chart").getContext('2d');

var sales_chart_bg_color = sales_chart.createLinearGradient(0, 0, 0, 80);
balance_chart_bg_color.addColorStop(0, 'rgba(63,82,227,.2)');
balance_chart_bg_color.addColorStop(1, 'rgba(63,82,227,0)');

var myChart = new Chart(sales_chart, {
  type: 'line',
  data: {
    labels: ['16-07-2018', '17-07-2018', '18-07-2018', '19-07-2018', '20-07-2018', '21-07-2018', '22-07-2018', '23-07-2018', '24-07-2018', '25-07-2018', '26-07-2018', '27-07-2018', '28-07-2018', '29-07-2018', '30-07-2018', '31-07-2018'],
    datasets: [{
      label: 'Sales',
      data: [70, 62, 44, 40, 21, 63, 82, 52, 50, 31, 70, 50, 91, 63, 51, 60],
      borderWidth: 2,
      backgroundColor: balance_chart_bg_color,
      borderWidth: 3,
      borderColor: 'rgba(63,82,227,1)',
      pointBorderWidth: 0,
      pointBorderColor: 'transparent',
      pointRadius: 3,
      pointBackgroundColor: 'transparent',
      pointHoverBackgroundColor: 'rgba(63,82,227,1)',
    }]
  },
  options: {
    layout: {
      padding: {
        bottom: -1,
        left: -1
      }
    },
    legend: {
      display: false
    },
    scales: {
      yAxes: [{
        gridLines: {
          display: false,
          drawBorder: false,
        },
        ticks: {
          beginAtZero: true,
          display: false
        }
      }],
      xAxes: [{
        gridLines: {
          drawBorder: false,
          display: false,
        },
        ticks: {
          display: false
        }
      }]
    },
  }
});

$("#products-carousel").owlCarousel({
  items: 3,
  margin: 10,
  autoplay: true,
  autoplayTimeout: 5000,
  loop: true,
  responsive: {
    0: {
      items: 2
    },
    768: {
      items: 2
    },
    1200: {
      items: 3
    }
  }
});

