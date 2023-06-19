var admGasStation = {
    addGasStationInList () {
        const reqUrl = "http://localhost:8080/gas-station";
        fetch(reqUrl)
            .then(response => response.json())
            .then((data) => {
                let content = data.content;
                const ulTag = document.getElementById("gas-station-list");

                for(var i = 0; i < content.length; i++){

                    const li = document.createElement("li");
                    const gasStationDiv = document.createElement("div");
                    const gasStationNameDiv = document.createElement("div");
                    const a = document.createElement("a");

                    a.setAttribute('id','gas-station');
                    a.setAttribute('name', content[i].gasStationId);
                    a.setAttribute('href', 'http://localhost:8080/map/'+content[i].gasStationId);

                    li.setAttribute('class', 'list-group-item d-flex justify-content-between align-items-start');
                    gasStationDiv.setAttribute('class', 'ms-2 me-auto');
                    gasStationNameDiv.setAttribute('class', 'fw-bold');

                    a.append(content[i].gasStationName);
                    gasStationNameDiv.append(a);
                    gasStationDiv.append(gasStationNameDiv);
                    li.append(gasStationDiv);
                    ulTag.append(li);
                }
            });
    },

    createNewGasStation() {
        const createBtn = document.getElementById('save-gas-station-btn');
        createBtn.addEventListener('click', () => {

            const postContent = {
                gasStationName : document.getElementById('gas-station-name').value,
                gasStationAddress : document.getElementById('gas-station-address').value,
                cleanCarFreePeriod : document.getElementById('clean-Car-Free-Period').value
            };

            const olTag = document.getElementById('gas-station-list');
            const li = document.createElement("li");
            const gasStationDiv = document.createElement("div");
            const gasStationNameDiv = document.createElement("div");
            const a = document.createElement("a");
            const postReqUrl = "http://localhost:8080/gas-station";
            fetch(postReqUrl, {
                method : "POST",
                body : JSON.stringify(postContent),
                headers : {
                    "Content-Type" : "application/json"
                }
            })
                .then(response => response.json())
                .then((data) => {

                    li.setAttribute('class', 'list-group-item d-flex justify-content-between align-items-start'),
                        gasStationDiv.setAttribute('class', 'ms-2 me-auto'),
                        gasStationNameDiv.setAttribute('class', 'fw-bold'),

                        a.setAttribute('id', 'gas-station'),
                        a.setAttribute('href', 'http://localhost:8080/map/' + data.gasStationId),

                        a.append(postContent.gasStationName),
                        gasStationNameDiv.append(a),
                        gasStationDiv.append(gasStationNameDiv),
                        li.append(gasStationDiv),
                        olTag.append(li)

                    if (data.cleanCarFreePeriod === 0) alert(`무료 세차권 이용 기간을 입력하지 않아 이용 기간이 ${data.cleanCarFreePeriod}일 로 설정되었습니다. 상세화면에서 수정할 수 있습니다.`)
                });
        });
    },

}


    function openCreateForm(){
    const visibleStatus = document.getElementById('createForm').className;
    if(visibleStatus === 'visible') document.getElementById('createForm').className = 'invisible';
    else document.getElementById('createForm').className = 'visible';
}