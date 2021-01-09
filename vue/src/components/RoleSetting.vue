<template>
<div>
    <div class="role-settings">
        <div class="image">
            imageがはいる予定(未実装)
        </div>
        <div class="setting-btn">
            <a  v-on:click="show">{{ role }}</a>
            <modal name="temp">
                <div class="modal-header">
                    <h2>未実装</h2>
                </div>
            </modal>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: "RoleSetting",
    props:['role'],
    data(){
        return {
            

        }
    },
    methods: {
        show: function () {
            this.$modal.show("temp");
        },
        hide: function () {
            this.$modal.hide("temp");
        },
    }
}
</script>

<style>

    .role-settings{
        margin: 1rem auto;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .role-settings a{
        display: inline-block;
        width: 8rem;
        background-color: white;
        padding: 2rem;
        margin: 0 2rem;
        text-decoration: none;
        color: #50A0F6;
        border:1px solid #50A0F6;
        border-radius: 1rem;
        }

    .role-settings .image{
        border: 1px solid #50A0F6;
        width: 400rem;
        height: 12rem;
    }


</style>