
let path = window.location.pathname;
const gasStationId = path.substring(5);

var GasStationDetail = {
    detailGasStation() {

        const reqUrl = "http://localhost:8080/gas-station/"+gasStationId;
        fetch(reqUrl)
            .then(response => response.json())
            .then((response) => {

                const contentBox = document.getElementById('content-box');
                const h3 = document.createElement("h3");
                const h4 = document.createElement("h4");
                const h5 = document.createElement("h5");

                h3.append(response.gasStationName);
                h4.append(response.gasStationAddress);
                h5.append(`무료 세차권 이용 가능 유효 기한 : ${response.cleanCarFreePeriod} 일`);
                contentBox.append(h3);
                contentBox.append(h4);
                contentBox.append(h5);

                const latY = response.latY.substring(0,9);
                const longX = response.longX.substring(0,10);

                var container = document.getElementById('map');
                var options = {
                    center: new kakao.maps.LatLng(latY, longX),
                    level: 4
                };
                var map = new kakao.maps.Map(container, options);
                var markerPosition  = new kakao.maps.LatLng(latY, longX);
                var marker = new kakao.maps.Marker({
                    position: markerPosition
                });
                marker.setMap(map);
            });

        const url = "http://localhost:8080/gas-station/clean/"+gasStationId;
        fetch(url)
            .then(response => response.json())
            .then((data) => {
                const content = data.content;
                const tbody = document.getElementById('clean-type-table-body');

                for (var i = 0; i < content.length; i++) {

                    const cleanCarTypeId = content[i].cleanCarTypeId;
                    const type = content[i].cleanType;
                    const price = content[i].price;
                    const defaultCondition = content[i].defaultCondition;

                    let bodyTag = '';
                    bodyTag += `<th scope="row">${i+1}</th>`;
                    bodyTag += `<td data-bs-whatever="type" class="type">${type}</td>`;
                    bodyTag += `<td data-bs-whatever="price" class="price">${price}</td>`;
                    bodyTag += `<td data-bs-whatever="condition" class="condition">${defaultCondition}</td>`;
                    bodyTag += `<td><input type="hidden" value="${cleanCarTypeId}"><button type='button' name="${cleanCarTypeId}"
                        class='btn btn-sm btn-outline-primary update-clean-type-btn' data-bs-toggle='modal'
                        data-bs-target='#update-clean-type-modal'>수정</button>
                        <input type="hidden" value="${cleanCarTypeId}"><button type='button' name="${cleanCarTypeId}"
                        class='btn btn-sm btn-outline-danger' id="delete-clean-type-btn">삭제</button></td>`;
                    tbody.innerHTML += bodyTag;
                }
                const deleteCleanTypeBtns = document.querySelectorAll("#delete-clean-type-btn");
                deleteCleanTypeBtns.forEach(btn => {
                    btn.addEventListener('click', (evt) => {
                        const deleteCleanTypeBtn = evt.target;

                        const cleanTypeId = deleteCleanTypeBtn.getAttribute("name");

                        const deleteReqUrl = 'http://localhost:8080/gas-station/clean/' + cleanTypeId;
                        fetch(deleteReqUrl, {
                            method: "DELETE"
                        })
                            .then(alert("삭제가 완료되었습니다."))
                            .then(deleteCleanTypeBtn.parentElement.parentElement.remove());

                    });
                });
            });

        const updateCleanTypeModal = document.getElementById('update-clean-type-modal')
        updateCleanTypeModal.addEventListener('show.bs.modal', event => {

            updateCleanTypeModal.querySelector('.modal-title').textContent = `수정하기`;

            const btn = event.relatedTarget
            const id = btn.getAttribute("name");

            const detailReqUrl = "http://localhost:8080/gas-station/clean-detail/" + id;
            fetch(detailReqUrl)
                .then(response => response.json())
                .then((data) => {
                    document.getElementById('hidden-id').value = id;
                    document.getElementById('type-input').value = data.cleanType;
                    document.getElementById('price-input').value = data.price;
                    document.getElementById('condition-input').value = data.defaultCondition;
                });
        })

        const updateCompleteBtn = document.getElementById("update-complete-btn-in-modal");
        updateCompleteBtn.addEventListener('click', () => {

            const content = {
                cleanCarTypeId: document.getElementById('hidden-id').value,
                cleanType : document.getElementById('type-input').value,
                price : document.getElementById('price-input').value,
                defaultCondition : document.getElementById('condition-input').value
            }

            const updateReqUrl = "http://localhost:8080/gas-station/clean";
            fetch(updateReqUrl, {

                method: "PUT",
                body: JSON.stringify(content),
                headers: {
                    "Content-Type": "application/json"
                }
            })
                .then(response => {
                    alert((response.ok) ? "수정이 완료되었습니다." : "수정 실패");
                    window.location.reload();
                })
        });

        const addCleanTypeCompleteBtn = document.getElementById("add-clean-car-type-complete-btn");
        addCleanTypeCompleteBtn.addEventListener('click', function () {

            const addCleanTypeContent = {
                cleanType : document.getElementById('cleanType-input').value,
                price : document.getElementById('cleanPrice-input').value,
                defaultCondition : document.getElementById('cleanDefaultCondition-input').value,
                gasStationId : gasStationId
            }
            const saveReqUrl = "http://localhost:8080/gas-station/clean";
            fetch(saveReqUrl, {
                method: "POST",
                body: JSON.stringify(addCleanTypeContent),
                headers: {
                    "Content-Type" : "application/json"
                },
            })
                .then(response => response.json())
                .then((data) => {

                    const tr = document.getElementById("clean-type-table-body");
                    let td = '';
                    td += `<th scope="row">${tr.children.length+1}</th>`;
                    td += `<td data-bs-whatever="type" class="type">${data.cleanType}</td>`;
                    td += `<td data-bs-whatever="price" class="price">${data.price}</td>`;
                    td += `<td data-bs-whatever="condition" class="condition">${data.defaultCondition}</td>`;
                    td += `<td><input type="hidden" value="${data.cleanCarTypeId}"><button type='button' name="${data.cleanCarTypeId}"
                        class='btn btn-sm btn-outline-primary update-clean-type-btn' data-bs-toggle='modal'
                        data-bs-target='#update-clean-type-modal' data-bs-whatever='@mdo'>수정</button>
                        <input type="hidden" value="${data.cleanCarTypeId}"><button type='button' name="${data.cleanCarTypeId}"
                        class='btn btn-sm btn-outline-danger' id="delete-clean-type-btn">삭제</button></td>`;
                    tr.innerHTML += td;
                    document.getElementById('addCleanCarTypeForm').className = 'invisible';
                })
        });
    },
};

function gasStationUpdate(){
    const updateContent = {
        gasStationId : gasStationId,
        gasStationName : document.getElementById('gasStationName').value,
        gasStationAddress : document.getElementById('gasStationAddress').value,
        cleanCarFreePeriod : document.getElementById('cleanCarFreePeriod').value
    };

    const updateReqUrl = "http://localhost:8080/gas-station";
    fetch(updateReqUrl, {
        method : "PUT",
        body : JSON.stringify(updateContent),
        headers : {
            "Content-Type" : "application/json"
        }
    })
        .then(response => {
            alert((response.ok) ? "수정 완료" : "수정 실패");
            window.location.reload();
        });
}

function gasStationDelete() {

    const deleteReqUrl = "http://localhost:8080/gas-station/"+gasStationId;
    fetch(deleteReqUrl, {
        method : "DELETE",
    }).then(
        alert("주유소 정보가 삭제되었습니다."),
        window.location.href='../admin',
    );
}

function openUpdateForm(){
    const updateFormVisibleStatus = document.getElementById('updateForm').className;
    if(updateFormVisibleStatus === 'visible') document.getElementById('updateForm').className = 'invisible';
    else document.getElementById('updateForm').className = 'visible';
}

function openCloseAddCleanTypeForm() {
    const visibleStatus = document.getElementById('addCleanCarTypeForm').className;
    if(visibleStatus === 'visible') document.getElementById('addCleanCarTypeForm').className = 'invisible';
    else document.getElementById('addCleanCarTypeForm').className = 'visible';
}


