function deleteAd(adId) {
    $.post("http://localhost:8080/сar_market/Ad/delete",
        {adId: adId}
    );
    document.getElementById("ad" + adId).style.display = "none";
}