var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: ['2023-04-21', '2023-04-22', '2023-04-23', '2023-04-24', '2023-04-25', '2023-04-26', '2023-04-27', '2023-04-28', '2023-04-29', '2023-04-30'],
    datasets: [
      {
        label: '질문게시글',
        data: [81, 68, 62, 75, 88, 72, 64, 78, 92, 85],
        borderWidth: 2,
        borderColor: '#6777ef',
        pointBackgroundColor: '#ffffff',
        pointRadius: 4,
        fill: false
      },
      {
        label: '질문댓글',
        data: [74, 67, 55, 62, 78, 68, 59, 65, 81, 72],
        borderWidth: 2,
        borderColor: '#fc544b',
        pointBackgroundColor: '#ffffff',
        pointRadius: 4,
        fill: false
      },
      {
        label: '스터디게시글',
        data: [51, 58, 65, 72, 64, 59, 71, 62, 55, 48],
        borderWidth: 2,
        borderColor: '#47c363',
        pointBackgroundColor: '#ffffff',
        pointRadius: 4,
        fill: false
      },
      {
        label: '스터디댓글',
        data: [29, 51, 47, 38, 32, 45, 33, 28, 41, 35],
        borderWidth: 2,
        borderColor: '#f3b760',
        pointBackgroundColor: '#ffffff',
        pointRadius: 4,
        fill: false
      }
    ]
  },
  options: {
    title: {
        display: true,
        text: '게시판 신고 통계',
        fontSize: 14, // 제목 글꼴 크기 (원하는 대로 조정 가능)
        fontColor: '#34395e'
      },
    legend: {
      display: true
    },
    scales: {
      yAxes: [
        {
          gridLines: {
            drawBorder: false,
            color: '#f2f2f2'
          },
          ticks: {
            beginAtZero: true,
            stepSize: 10,
            max: 100
          }
        }
      ],
      xAxes: [
        {
          ticks: {
            display: true
          },
          gridLines: {
            display: false
          }
        }
      ]
    }
  }
});