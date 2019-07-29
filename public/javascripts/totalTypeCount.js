var ctx = document.getElementById('totalTypeCount').getContext('2d');

var labelText = document.getElementById('totalTypeCount').getAttribute('data-label-text');
var dataPoints = document.getElementById('totalTypeCount').getAttribute('data-points').split("|");
var dataLabels = document.getElementById('totalTypeCount').getAttribute('data-labels').split("|");

var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: dataLabels,
        datasets: [{
            label: labelText,
            data: dataPoints,
            backgroundColor: [
                'rgba(255, 99, 132, 0.8)',
                'rgba(54, 162, 235, 0.8)',
                'rgba(255, 206, 86, 0.8)',
                'rgba(75, 192, 192, 0.8)',
                'rgba(153, 102, 255, 0.8)',
                'rgba(255, 159, 64, 0.8)'
            ],
            borderColor: [
                'rgba(0, 0, 0, 1)',
                'rgba(0, 0, 0, 1)',
                'rgba(0, 0, 0, 1)',
                'rgba(0, 0, 0, 1)',
                'rgba(0, 0, 0, 1)',
                'rgba(0, 0, 0, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        legend: {
                display: false
            },
            title: {
                display: true,
                text: 'Total Category Count',
                fontSize: 16
            }
    }
});