import { useEffect } from "react";
import { useAmizade } from "../../../api/amizade/pedidosAmizade";
import './index.css'

export function AmizadesScreen() {
  const { getAmizade, pedidos } = useAmizade();

  useEffect(() => {
    getAmizade();
  }, []);

  console.log(pedidos);

  return (
    <>
    <div className="pedidoAmizade">
      {pedidos &&
        pedidos.map((pedido, index) => {
          return (
            <div className="post amizades" key={index}>
              <div className="postPerfil">
                <img
                  className="imgProfile"
                  src={pedido?.fotoRemetente}
                  alt="Foto do perfil"
                />
                <h1>
                  {pedido.apelidoRemetente ? pedido.apelidoRemetente : pedido.nomeRemetente}
                </h1>

              </div>
              <h1>{pedido.statusAmizade }</h1>
            </div>
          );
        })}
        </div>
    </>
  );
}
