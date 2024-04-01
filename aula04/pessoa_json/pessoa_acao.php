<?php

/*
* Código adaptado a partir do código do professor Rodrigo Curvello
* Controlador reponsável pela manutenção do cadastro da entidade Pessoa
* @author Wesley R. Bezerra <wesley.bezerra@ifc.edu.br>
* @version 0.1
*
*/

/* definição de constantes */
define("DESTINO", "index.php");
//define("ARQUIVO_JSON", "pessoa.json");
define("ARQUIVO_JSON", "pessoa.xml");

/* escolha da ação que processará a requisição */
$acao = "";
switch ($_SERVER['REQUEST_METHOD']) {
    case 'GET':
        $acao = isset($_GET['acao']) ? $_GET['acao'] : "";
        break;
    case 'POST':
        $acao = isset($_POST['acao']) ? $_POST['acao'] : "";
        break;
}

switch ($acao) {
    case 'Salvar':
        salvar();
        break;
    case 'Alterar':
        alterar();
        break;
    case 'excluir':
        excluir();
        break;
}

/*
* Método que converte formulário html para array com respectivos dados
* @return array
*/
function tela2array()
{
    $novo = array(
        'id' => isset($_POST['id']) ? $_POST['id'] : date("YmdHis"),
        'nome' => isset($_POST['nome']) ? $_POST['nome'] : "",
        'peso' => isset($_POST['peso']) ? $_POST['peso'] : "",
        'altura' => isset($_POST['altura']) ? $_POST['altura'] : ""
    );
    if ($novo['id'] == "0") {
        $novo['id'] = date("YmdHis");
    }
    return $novo;
}

/*
* Método que converte array para json
* @return String json
*/
function array2json($array_dados, $json_dados)
{
    $json_dados->id = $array_dados['id'];
    $json_dados->nome = $array_dados['nome'];
    $json_dados->peso = $array_dados['peso'];
    $json_dados->altura = $array_dados['altura'];

    return $json_dados;
}
/*
* Método que salva os dados no formato json no arquivo em disco
* @param $dados String dados codificados no formato json
* @param $arquivo String nome do arquivo onde serão salvos os dados
* @return void
*/
function salvar_json($dados, $arquivo)
{
    $fp = fopen($arquivo, "w");
    fwrite($fp, $dados);
    fclose($fp);
}
/*
* Método que lê os dados no formato json do arquivo em disco
* @param $arquivo String nome do arquivo onde serão salvos os dados
* @return String dados codificados no formato json
*/
function ler_json($arquivo)
{
    $arquivo = file_get_contents($arquivo);
    $json = json_decode($arquivo);
    return $json;
}

function ler_xml($arquivo) {
    $arquivo = file_get_contents($arquivo);
    $xml = new SimpleXMLElement($arquivo);
    return $xml;
    
}

/*
* Método que lê os dados e os carrega em um variável chamada json
* @param $id int identificador numérico do registro
* @return String dados codificados no formato json
*/
function carregar($id)
{
    $json = ler_json(ARQUIVO_JSON);

    foreach ($json as $key) {
        if ($key->id == $id)
            return (array) $key;
    }
}

/*
* Método que altera os dados de um registro
* @return void
*/
function alterar()
{
    $novo = tela2array();

    $json = ler_json(ARQUIVO_JSON);

    for ($x = 0; $x < count($json); $x++) {
        if ($json[$x]->id == $novo['id']) {
            array2json($novo, $json[$x]);
        }
    }

    salvar_json(json_encode($json), ARQUIVO_JSON);

    header("location:" . DESTINO);

}


/*
1 - abrir json em formato php;
2 - percorrer e achar o item pelo ID;
3 - estratégia de deletar;
4 - gravar em json novamente, sem o item;
5 - redirecionar para a página index.php
*/

/*
* Método exclui um registro
* @return void
*/
function excluir()
{
    $id = isset($_GET['id']) ? $_GET['id'] : "";
    $json = ler_json(ARQUIVO_JSON);
    if ($json == null)
        $json = array();

    $novo = array();
    for ($x = 0; $x < count($json); $x++) {
        var_dump($json[$x]);
        if ($json[$x]->id != $id)

            array_push($novo, $json[$x]);
    }
    salvar_json(json_encode($novo), ARQUIVO_JSON);

    header("location:" . DESTINO);

}
/*
* Método salva alterações feitas em um registro
* @return void
*/
function salvar()
{
    $json = NULL;
    $pessoa = tela2array();

    $json = ler_json(ARQUIVO_JSON);

    if ($json == NULL) {
        $json = [];
    }

    array_push($json, $pessoa);

//    salvar_json(json_encode($json), ARQUIVO_JSON);
    $xml = ler_xml(ARQUIVO_JSON);
    salvar_json(arrayToXml($json, $xml), ARQUIVO_JSON);

    header("location:" . DESTINO);
}

// /**
//  * Convert an array to XML
//  * @param array $array
//  * @param SimpleXMLElement $xml
//  * @return String
//  */
function arrayToXml($array, &$xml){
    foreach ($array as $key => $value) {
        if(is_int($key)){
            $key = "e";
        }
        if(is_array($value)){
            $label = $xml->addChild($key);
            arrayToXml($value, $label);
        }
        else {
            $xml->addChild($key, $value);
        }
    }
    return $xml->asXML();
}

