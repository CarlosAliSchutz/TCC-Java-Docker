import { axiosInstance } from "../_base/axios-instance";
import { useRequest } from "../_base/use-request";

export const useTodosPosts = () => {
  const { handleRequest, data } = useRequest();

  function getAllPosts() {
    handleRequest(axiosInstance.get("/posts/listarTodos"));
  }
  return { getAllPosts, posts: data };
};
