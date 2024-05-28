<!DOCTYPE html>
<html>
<head>
    <title>Travel Itinerary</title>
    <script src='https://api.mapbox.com/mapbox-gl-js/v2.7.0/mapbox-gl.js'></script>
    <link href='https://api.mapbox.com/mapbox-gl-js/v2.7.0/mapbox-gl.css' rel='stylesheet' />
    <style>
        #map { width: 100%; height: 400px; }
    </style>
</head>
<body>
    <h1>Travel Itinerary</h1>
    <div id='map'></div>
    <script>
        mapboxgl.accessToken = 'pk.eyJ1IjoiaGFyc2hpdGFhIiwiYSI6ImNsdWIzend1ZjBjeGoyaG5zZXB5enNndmQifQ.QYa70ShjJoQFqO2EsGO-0Q';
        var map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/mapbox/streets-v11',
            center: [77.5946, 12.9716], 
            zoom: 9 
        });
    </script>
    <p>${itinerary}</p>
      <p>Total Budget: $${totalBudget}</p>
</body>
</html>
