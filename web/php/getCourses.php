<?php
/**
 * Created by PhpStorm.
 * User: lenovo
 * Date: 2016/6/11
 * Time: 16:17
 */
$array=array(
    array('id'=>3,'name'=>'art','day'=>'3','begin'=>'7','end'=>'8','capacity'=>'10'),
    array('id'=>4,'name'=>'music','day'=>'4','begin'=>'9','end'=>'10','capacity'=>'10')
);
echo json_encode($array);