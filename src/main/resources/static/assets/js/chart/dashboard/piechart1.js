var pieChart1 = document.getElementById("pieChart1").getContext("2d");

// Récupération des données Thymeleaf
var heures = /*[[${statsReservations.![heureDebut]}]]*/ [];
var counts = /*[[${statsReservations.![count]}]]*/ [];

// Couleurs dynamiques pour les différentes heures
var colors = ["#1d7af3", "#f3545d", "#fdaf4b", "#2dce89", "#f5b041", "#a569bd"];

// Création du Pie Chart
var myPieChart1 = new Chart(pieChart1, {
  type: "pie",
  data: {
    datasets: [
      {
        data: counts,
        backgroundColor: colors.slice(0, counts.length), // Sélectionne autant de couleurs que nécessaire
        borderWidth: 0,
      },
    ],
    labels: heures, // Labels des heures
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    legend: {
      position: "bottom",
      labels: {
        fontColor: "rgb(154, 154, 154)",
        fontSize: 11,
        usePointStyle: true,
        padding: 20,
      },
    },
    plugins: {
      tooltip: {
        enabled: true
      },
      datalabels: {
        color: "white",
        font: {
          size: 14
        },
        formatter: (value, context) => {
          return value + " réservations"; // Affiche le nombre de réservations
        }
      }
    },
    layout: {
      padding: {
        left: 20,
        right: 20,
        top: 20,
        bottom: 20,
      },
    },
  },
});
