import {createRoot} from "react-dom/client";
import {RoomPage} from "@/features/room/RoomPage";

const container = document.getElementById('jsi-entry');
const root = createRoot(container!);

root.render(
    <RoomPage/>
);