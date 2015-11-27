-- Function: atualizarvaloresdeextrato()

-- DROP FUNCTION atualizarvaloresdeextrato();

CREATE OR REPLACE FUNCTION atualizarvaloresdeextrato()
  RETURNS trigger AS
$BODY$
	DECLARE
		valor real;
		diferenca real;
		extrato "Extrato"%rowtype;
	BEGIN
		IF(TG_OP = 'DELETE') THEN
			UPDATE "Extrato"
			SET "valorFinal" = OLD."valorFinal"
			WHERE "idExtrato" = OLD."idExtrato";
			return old;
		ELSE
			valor = NEW."valorFinal";
			FOR extrato IN 
				SELECT * FROM "Extrato"
				WHERE extrato."idConta" = NEW."idConta"
				AND extrato."idExtrato" > NEW."idExtrato"
				ORDER BY "idExtrato" DESC
			LOOP
				diferenca = extrato."valorFinal" - extrato."valorInicial";
				extrato."valorInicial" = valor;
				extrato."valorFinal" = valor + diferenca;
				valor = extrato."valorFinal";
			END LOOP;
			return new;			
		END IF;
	END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION atualizarvaloresdeextrato()
  OWNER TO postgres;

CREATE TRIGGER atualizarvaloresdeextratoporatualizacao
	AFTER INSERT OR UPDATE ON "Extrato"
	FOR EACH ROW
	EXECUTE PROCEDURE atualizarvaloresdeextrato();
	
CREATE TRIGGER atualizarvaloresdeextratoporremocao
	BEFORE DELETE ON "Extrato"
	FOR EACH ROW
	EXECUTE PROCEDURE atualizarvaloresdeextrato();