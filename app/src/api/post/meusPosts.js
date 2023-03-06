import { axiosInstance } from "../_base/axios-instance";
import { useRequest } from "../_base/use-request";

export const useMeusPosts = () => {
  const { handleRequest, data } = useRequest();

  function getPosts() {
    handleRequest(axiosInstance.get("/posts"));
  }
  return { getPosts, posts: data };
};
