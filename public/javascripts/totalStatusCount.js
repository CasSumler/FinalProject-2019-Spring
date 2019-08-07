var ctx = document.getElementById('totalStatusCount').getContext('2d');

var labelText = document.getElementById('totalStatusCount').getAttribute('data-label-text');
var dataPoints = document.getElementById('totalStatusCount').getAttribute('data-points').split("|");
var dataLabels = document.getElementById('totalStatusCount').getAttribute('data-labels').split("|");

var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: dataLabels,
        datasets: [{
            label: labelText,
            data: dataPoints,
            backgroundColor: [
                'rgba(150, 206, 180, 0.8)',
                'rgba(217, 100, 89, 0.8)',
                'rgba(255, 238, 173, 0.8)',
                'rgba(242, 174, 114, 0.8)',
                'rgba(255, 204, 92, 0.8)',
                'rgba(242, 227, 148, 0.8)',
                'rgba(255, 111, 105, 0.8)',
                'rgba(88, 140, 126, 0.8)',
                'rgba(150, 206, 180, 0.8)',
                'rgba(217, 100, 89, 0.8)',
                'rgba(255, 238, 173, 0.8)',
                'rgba(242, 174, 114, 0.8)',
                'rgba(255, 204, 92, 0.8)',
                'rgba(242, 227, 148, 0.8)',
                'rgba(255, 111, 105, 0.8)',
                'rgba(88, 140, 126, 0.8)',
                'rgba(150, 206, 180, 0.8)',
                'rgba(217, 100, 89, 0.8)',
                'rgba(255, 238, 173, 0.8)',
                'rgba(242, 174, 114, 0.8)',
                'rgba(255, 204, 92, 0.8)',
                'rgba(242, 227, 148, 0.8)',
                'rgba(255, 111, 105, 0.8)',
                'rgba(88, 140, 126, 0.8)'
            ],
            borderColor: [
                'rgba(0, 0, 0, 1)',
                'rgba(0, 0, 0, 1)',
                'rgba(0, 0, 0, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
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
                text: 'Total Status Count',
                fontSize: 18
            }
    }
});